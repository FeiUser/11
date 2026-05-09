package com.noweb.phone;


import com.noweb.utils.BriSDKLib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class RealTimeAudioCollector {
    
    private int channel;
    private AtomicBoolean isCollecting = new AtomicBoolean(false);
    private String currentFilePath;
    private AudioDataCallback callback;
    private AudioStreamReader streamReader;
    
    public RealTimeAudioCollector(int channel, AudioDataCallback callback) {
        this.channel = channel;
        this.callback = callback;
    }
    
    public void startCollection() {
        if (isCollecting.get()) {
            System.out.println("已经在采集中");
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        currentFilePath = "temp_audio_" + sdf.format(new Date()) + ".wav";
        
        int result = BriSDKLib.QNV_RecordFile(
            channel,
            BriSDKLib.QNV_RECORD_FILE_START,
            BriSDKLib.BRI_WAV_FORMAT_PCM8K8B,
            BriSDKLib.RECORD_MASK_ECHO,
            currentFilePath
        );
        
        if (result > 0) {
            isCollecting.set(true);
            streamReader = new AudioStreamReader(channel, currentFilePath, callback);
            streamReader.start();
            System.out.println("实时音频采集已启动，文件：" + currentFilePath);
        } else {
            System.err.println("实时音频采集启动失败：" + result);
        }
    }
    
    public void stopCollection() {
        if (!isCollecting.get()) {
            return;
        }
        
        isCollecting.set(false);
        
        if (streamReader != null) {
            streamReader.stopReading();
        }
        
        if (currentFilePath != null) {
            File file = new File(currentFilePath);
            if (file.exists()) {
                System.out.println("删除临时文件：" + currentFilePath);
                file.delete();
            }
        }
        
        currentFilePath = null;
        System.out.println("实时音频采集已停止");
    }
    
    public boolean isCollecting() {
        return isCollecting.get();
    }
    
    public String getCurrentFilePath() {
        return currentFilePath;
    }
    
    public interface AudioDataCallback {
        void onAudioData(byte[] data);
        void onError(Exception e);
    }
    
    private class AudioStreamReader extends Thread {
        
        private int channel;
        private String filePath;
        private AudioDataCallback callback;
        private AtomicBoolean reading = new AtomicBoolean(false);
        private long lastReadPosition = 0;
        
        public AudioStreamReader(int channel, String filePath, AudioDataCallback callback) {
            this.channel = channel;
            this.filePath = filePath;
            this.callback = callback;
        }
        
        @Override
        public void run() {
            reading.set(true);
            File file = new File(filePath);
            
            if (file.exists()) {
                lastReadPosition = writeWavHeader(file);
            }
            
            while (reading.get() && isCollecting.get()) {
                try {
                    if (file.exists()) {
                        long fileSize = file.length();
                        if (fileSize > lastReadPosition + 160) {
                            byte[] audioData = readNewAudioData(file);
                            if (audioData != null && callback != null) {
                                callback.onAudioData(audioData);
                            }
                        }
                    }
                    Thread.sleep(20);
                } catch (Exception e) {
                    if (reading.get() && callback != null) {
                        callback.onError(e);
                    }
                }
            }
        }
        
        private long writeWavHeader(File file) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] header = new byte[44];
                fis.read(header);
                return 44;
            } catch (IOException e) {
                return 0;
            }
        }
        
        private byte[] readNewAudioData(File file) {
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.skip(lastReadPosition);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] data = new byte[160];
                int bytesRead;
                
                while ((bytesRead = fis.read(data)) != -1 && buffer.size() < 640) {
                    buffer.write(data, 0, bytesRead);
                }
                
                lastReadPosition += buffer.size();
                return buffer.toByteArray();
            } catch (IOException e) {
                return null;
            }
        }
        
        public void stopReading() {
            reading.set(false);
        }
    }
}