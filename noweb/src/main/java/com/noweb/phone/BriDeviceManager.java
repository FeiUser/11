package com.noweb.phone;

import com.noweb.utils.BriSDKLib;

public class BriDeviceManager {
    
    private boolean isDeviceOpened = false;
    private int channelCount = 0;
    
    public boolean initializeDevice() {
        try {
            // 检查JVM位数
            String jvmArch = System.getProperty("sun.arch.data.model");
            System.out.println("JVM位数: " + jvmArch);

            // 检查系统类型
            String osArch = System.getProperty("os.arch");
            System.out.println("系统架构: " + osArch);

            int result = BriSDKLib.QNV_OpenDevice(BriSDKLib.ODT_ALL, 0, "0");
            if (result > 0) {
                isDeviceOpened = true;
                channelCount = BriSDKLib.QNV_DevInfo(-1, BriSDKLib.QNV_DEVINFO_GETCHANNELS);
                System.out.println("设备打开成功，通道数量：" + channelCount);
                initializeChannels();
                return true;
            }
        } catch (Exception e) {
            System.err.println("设备初始化失败：" + e.getMessage());
        }
        return false;
    }
    
    private void initializeChannels() {
        for (int i = 0; i < channelCount; i++) {
            BriSDKLib.QNV_SetParam(i, BriSDKLib.QNV_PARAM_AM_LINEIN, 5);
            BriSDKLib.QNV_SetDevCtrl(i, BriSDKLib.QNV_CTRL_READFRAMENUM, 8);
        }
    }
    
    public void closeDevice() {
        if (isDeviceOpened) {
            BriSDKLib.QNV_CloseDevice(BriSDKLib.ODT_ALL, 0);
            isDeviceOpened = false;
            System.out.println("设备已关闭");
        }
    }
    
    public boolean isDeviceOpened() {
        return isDeviceOpened;
    }
    
    public int getChannelCount() {
        return channelCount;
    }
    
    public int getHookState(int channel) {
        return BriSDKLib.QNV_GetDevCtrl(channel, BriSDKLib.QNV_CTRL_PHONE);
    }
    
    public void setHook(int channel, boolean onHook) {
        BriSDKLib.QNV_SetDevCtrl(channel, BriSDKLib.QNV_CTRL_DOHOOK, onHook ? 1 : 0);
    }
    
    public int startRecording(int channel, String filePath) {
        return BriSDKLib.QNV_RecordFile(channel, BriSDKLib.QNV_RECORD_FILE_START, 
                                        BriSDKLib.BRI_WAV_FORMAT_PCM8K8B,
                                        BriSDKLib.RECORD_MASK_ECHO, filePath);
    }
    
    public void stopRecording(int channel, int handle) {
        if (handle > 0) {
            BriSDKLib.QNV_RecordFile(channel, BriSDKLib.QNV_RECORD_FILE_STOP, handle, 0, null);
        }
    }
    
    public int getEvent(int channel) {
        return BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_TYPE, 0, null, null, 0);
    }
    
    public void removeEvent(int channel) {
        BriSDKLib.QNV_Event(channel, BriSDKLib.QNV_EVENT_REMOVE, 0, null, null, 0);
    }
}