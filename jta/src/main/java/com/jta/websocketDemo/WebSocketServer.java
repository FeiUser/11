package com.jta.websocketDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * @ Auther: 马超伟
 * @ Date: 2020/06/16/14:35
 * @ Description:
 * @ ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    // 存储用户流量信息
    private static ConcurrentHashMap<String, UserTraffic> userTrafficMap = new ConcurrentHashMap<>();

    // 定时任务调度器
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid = "";

    static {
        // 启动定时推送任务
        scheduler.scheduleAtFixedRate(() -> {
            try {
                for (String userSid : userTrafficMap.keySet()) {
                    sendTrafficUpdate(userSid);
                }
            } catch (IOException e) {
                log.error("定时发送流量信息失败", e);
            }
        }, 0, 1, TimeUnit.MINUTES); // 每分钟推送一次
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        this.sid = sid;
        addOnlineCount();           //在线数加1
        try {
            sendMessage("conn_success");
            log.info("有新窗口开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        //断开连接情况下，更新主板占用情况为释放
        log.info("释放的sid为："+sid);
        //这里写你 释放的时候，要处理的业务
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);

        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
//                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static void sendTrafficUpdate(String sid) throws IOException {
        Session session = sessionMap.get(sid);
        UserTraffic userTraffic = userTrafficMap.get(sid);
        if (session != null && session.isOpen() && userTraffic != null) {
            String message = "流量使用情况: 已用流量 " + userTraffic.getUsedTraffic() +
                    " / 总流量 " + userTraffic.getTotalTraffic() +
                    "，剩余流量 " + userTraffic.getRemainingTraffic();
            session.getBasicRemote().sendText(message);
            log.info("已推送流量信息给用户 " + sid);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }


    class UserTraffic {
        private int totalTraffic; // 总流量
        private int usedTraffic;  // 已使用流量

        public UserTraffic(int totalTraffic) {
            this.totalTraffic = totalTraffic;
            this.usedTraffic = 0;
        }

        public void addUsage(int usage) {
            this.usedTraffic += usage;
        }

        public int getUsedTraffic() {
            return usedTraffic;
        }

        public int getTotalTraffic() {
            return totalTraffic;
        }

        public int getRemainingTraffic() {
            return totalTraffic - usedTraffic;
        }
    }
}