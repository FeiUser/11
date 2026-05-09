package com.noweb.phone;

import com.noweb.utils.BriSDKLib;

import java.util.concurrent.atomic.AtomicBoolean;

public class PhoneEventProcessor extends Thread {
    
    private int channelCount;
    private int[] recordFileHandles;
    private AtomicBoolean running = new AtomicBoolean(false);
    private EventCallback eventCallback;
    
    public PhoneEventProcessor(int channelCount, EventCallback callback) {
        this.channelCount = channelCount;
        this.eventCallback = callback;
        this.recordFileHandles = new int[channelCount];
        for (int i = 0; i < channelCount; i++) {
            recordFileHandles[i] = -1;
        }
    }
    
    @Override
    public void run() {
        running.set(true);
        System.out.println("事件监听线程已启动");
        
        while (running.get()) {
            try {
                for (int i = 0; i < channelCount; i++) {
                    processChannelEvents(i);
                }
                Thread.sleep(200);
            } catch (Exception e) {
                System.err.println("事件处理异常：" + e.getMessage());
            }
        }
        
        System.out.println("事件监听线程已停止");
    }
    
    private void processChannelEvents(int channel) {
        int eventType = BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_TYPE, 0, null, null, 0);
        
        if (eventType > 0) {
            int eventHandle = BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_HANDLE, 0, null, null, 0);
            int eventResult = BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_RESULT, 0, null, null, 0);
            
            StringBuffer dataBuffer = new StringBuffer(1024);
            BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_DATA, 0, null, dataBuffer, 1024);
            
            BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_REMOVE, 0, null, null, 0);
            
            handleEvent(channel, eventType, eventHandle, eventResult, dataBuffer.toString());
        }
    }
    
    private void handleEvent(int channel, int eventType, int handle, int result, String data) {
        switch (eventType) {
            case BriSDKLib.BriEvent_PhoneHook:
                System.out.println("通道" + channel + "：本地电话摘机");
                if (eventCallback != null) {
                    eventCallback.onHookOn(channel);
                }
                break;
                
            case BriSDKLib.BriEvent_PhoneHang:
                System.out.println("通道" + channel + "：本地电话挂机");
                if (eventCallback != null) {
                    eventCallback.onHookOff(channel);
                }
                break;
                
            case BriSDKLib.BriEvent_EnableHook:
                if (result == 0) {
                    System.out.println("通道" + channel + "：软摘机成功");
                    if (eventCallback != null) {
                        eventCallback.onHookOn(channel);
                    }
                } else {
                    System.out.println("通道" + channel + "：软挂机成功");
                    if (eventCallback != null) {
                        eventCallback.onHookOff(channel);
                    }
                }
                break;
                
            case BriSDKLib.BriEvent_RemoteHook:
                System.out.println("通道" + channel + "：对方摘机");
                if (eventCallback != null) {
                    eventCallback.onRemoteHookOn(channel);
                }
                break;
                
            case BriSDKLib.BriEvent_RemoteHang:
                System.out.println("通道" + channel + "：对方挂机");
                if (eventCallback != null) {
                    eventCallback.onRemoteHookOff(channel);
                }
                break;
                
            case BriSDKLib.BriEvent_Busy:
                System.out.println("通道" + channel + "：检测到忙音");
                if (eventCallback != null) {
                    eventCallback.onBusy(channel);
                }
                break;
                
            case BriSDKLib.BriEvent_GetCallID:
                System.out.println("通道" + channel + "：来电号码：" + data);
                if (eventCallback != null) {
                    eventCallback.onCallID(channel, data);
                }
                break;
        }
    }
    
    public void stopProcessing() {
        running.set(false);
    }
    
    public interface EventCallback {
        void onHookOn(int channel);
        void onHookOff(int channel);
        void onRemoteHookOn(int channel);
        void onRemoteHookOff(int channel);
        void onBusy(int channel);
        void onCallID(int channel, String callID);
    }
}