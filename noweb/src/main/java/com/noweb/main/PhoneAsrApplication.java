package com.noweb.main;


import com.noweb.phone.AsrWebSocketClient;
import com.noweb.phone.BriDeviceManager;
import com.noweb.phone.PhoneEventProcessor;
import com.noweb.phone.RealTimeAudioCollector;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PhoneAsrApplication {
    
    private BriDeviceManager deviceManager;
    private PhoneEventProcessor eventProcessor;
    private AsrWebSocketClient webSocketClient;
    private RealTimeAudioCollector audioCollector;
    private AtomicBoolean isInCall = new AtomicBoolean(false);
    private int currentChannel = 0;
    private String asrServerUrl = "ws://your-asr-server/ws";
    
    public static void main(String[] args) {
        PhoneAsrApplication app = new PhoneAsrApplication();
        app.start();
    }
    
    public void start() {
        System.out.println("=== 电话实时转写系统启动 ===");
        
        initializeDevice();
        
        initializeWebSocket();
        
        startEventListening();
        
        registerShutdownHook();
        
        System.out.println("系统已就绪，等待电话事件...");
    }
    
    private void initializeDevice() {
        deviceManager = new BriDeviceManager();
        if (!deviceManager.initializeDevice()) {
            System.err.println("设备初始化失败，系统退出");
            System.exit(1);
        }
    }
    
    private void initializeWebSocket() {
        try {
            URI uri = new URI(asrServerUrl);
            webSocketClient = new AsrWebSocketClient(uri, new AsrWebSocketClient.AsrCallback() {
                @Override
                public void onConnected() {
                    System.out.println("ASR WebSocket已连接");
                }
                
                @Override
                public void onDisconnected() {
                    System.out.println("ASR WebSocket已断开");
                }
                
                @Override
                public void onTranscriptionResult(String text, double confidence) {
                    System.out.println("转写结果: " + text + " (置信度: " + confidence + ")");
                }
                
                @Override
                public void onBinaryMessage(byte[] data) {
                    System.out.println("收到二进制消息，长度: " + data.length);
                }
                
                @Override
                public void onError(Exception e) {
                    System.err.println("ASR WebSocket错误: " + e.getMessage());
                }
            });
            
            webSocketClient.connect();
            
        } catch (URISyntaxException e) {
            System.err.println("WebSocket URL格式错误: " + e.getMessage());
        }
    }
    
    private void startEventListening() {
        eventProcessor = new PhoneEventProcessor(deviceManager.getChannelCount(), 
            new PhoneEventProcessor.EventCallback() {
                
                @Override
                public void onHookOn(int channel) {
                    handleHookOn(channel);
                }
                
                @Override
                public void onHookOff(int channel) {
                    handleHookOff(channel);
                }
                
                @Override
                public void onRemoteHookOn(int channel) {
                    System.out.println("通道" + channel + "对方已摘机");
                }
                
                @Override
                public void onRemoteHookOff(int channel) {
                    handleHookOff(channel);
                }
                
                @Override
                public void onBusy(int channel) {
                    handleHookOff(channel);
                }
                
                @Override
                public void onCallID(int channel, String callID) {
                    System.out.println("通道" + channel + "来电: " + callID);
                }
            });
        
        eventProcessor.start();
    }
    
    private synchronized void handleHookOn(int channel) {
        if (isInCall.get()) {
            System.out.println("已经在通话中，忽略重复摘机事件");
            return;
        }
        
        currentChannel = channel;
        isInCall.set(true);
        
        System.out.println("=== 摘机事件，开始实时转写 ===");
        
        if (webSocketClient != null && !webSocketClient.isOpen()) {
            try {
                webSocketClient.reconnectBlocking();
            } catch (Exception e) {
                System.err.println("重新连接WebSocket失败: " + e.getMessage());
            }
        }
        
        webSocketClient.startRecording();
        
        audioCollector = new RealTimeAudioCollector(channel, 
            new RealTimeAudioCollector.AudioDataCallback() {
                @Override
                public void onAudioData(byte[] data) {
                    if (webSocketClient != null && webSocketClient.isOpen()) {
                        webSocketClient.sendAudioData(data);
                    }
                }
                
                @Override
                public void onError(Exception e) {
                    System.err.println("音频采集错误: " + e.getMessage());
                }
            });
        
        audioCollector.startCollection();
        
        System.out.println("实时音频采集已启动");
    }
    
    private synchronized void handleHookOff(int channel) {
        if (!isInCall.get()) {
            System.out.println("不在通话中，忽略重复挂机事件");
            return;
        }
        
        System.out.println("=== 挂机事件，停止实时转写 ===");
        
        if (audioCollector != null && audioCollector.isCollecting()) {
            audioCollector.stopCollection();
            audioCollector = null;
        }
        
        webSocketClient.stopRecording();
        
        isInCall.set(false);
        
        System.out.println("实时音频采集已停止");
    }
    
    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("系统正在关闭...");
            
            if (audioCollector != null && audioCollector.isCollecting()) {
                audioCollector.stopCollection();
            }
            
            if (eventProcessor != null) {
                eventProcessor.stopProcessing();
            }
            
            if (webSocketClient != null) {
                webSocketClient.stopRecording();
                webSocketClient.close();
            }
            
            if (deviceManager != null) {
                deviceManager.closeDevice();
            }
            
            System.out.println("系统已关闭");
        }));
    }
    
    public void setAsrServerUrl(String url) {
        this.asrServerUrl = url;
    }
    
    public boolean isInCall() {
        return isInCall.get();
    }
    /**
     * 大概任务流程：
         * 员工1负责前台：与后台建立websocket连接，实时展示音频流转写后的文字。
         * 员工2负责java后台：通过第三方的文档，实时接收音频流并将实时的音频流通过websocket给到员工3的转写服务，转写服务实时的把转写后的文字给到java后台，java后台再通过websocket实时的给到前端展示
         * 员工3负责转写服务：实时转写，已通过websocket实现，传输音频流即可返回转写的音频文字。
     * 细节：
         * 员工2的java后台在接收音频流时，可能有多个电话同时摘机，或者挂机。
         * 系统中登录的每个用户，会绑定一个电话，改电话的实时音频转写只会给到与之绑定的用户
     */
}