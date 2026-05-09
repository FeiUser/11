package com.noweb.phone;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;

public class AsrWebSocketClient extends WebSocketClient {
    
    private boolean isRecording = false;
    private ByteArrayOutputStream audioBuffer = new ByteArrayOutputStream();
    private AsrCallback callback;
    
    public AsrWebSocketClient(URI serverUri, AsrCallback callback) {
        super(serverUri);
        this.callback = callback;
    }
    
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WebSocket连接已建立");
        if (callback != null) {
            callback.onConnected();
        }
    }
    
    @Override
    public void onMessage(String message) {
        System.out.println("收到ASR转写结果：" + message);
        try {
            JSONObject json = new JSONObject(message);
            if (callback != null) {
                callback.onTranscriptionResult(
                    json.optString("text", ""),
                    json.optDouble("confidence", 0.0)
                );
            }
        } catch (Exception e) {
            System.err.println("解析转写结果失败：" + e.getMessage());
        }
    }
    
    @Override
    public void onMessage(ByteBuffer bytes) {
        if (callback != null) {
            callback.onBinaryMessage(bytes.array());
        }
    }
    
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket连接关闭，代码：" + code + "，原因：" + reason);
        if (callback != null) {
            callback.onDisconnected();
        }
    }
    
    @Override
    public void onError(Exception ex) {
        System.err.println("WebSocket错误：" + ex.getMessage());
        if (callback != null) {
            callback.onError(ex);
        }
    }
    
    public void sendAudioData(byte[] audioData) {
        if (isOpen() && isRecording && audioData != null && audioData.length > 0) {
            send(audioData);
        }
    }
    
    public void startRecording() {
        this.isRecording = true;
        this.audioBuffer.reset();
    }
    
    public void stopRecording() {
        this.isRecording = false;
    }
    
    public void sendAudioFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                if (isOpen()) {
                    send(ByteBuffer.wrap(buffer, 0, bytesRead));
                }
            }
        } catch (IOException e) {
            System.err.println("读取音频文件失败：" + e.getMessage());
        }
    }
    
    public boolean isRecording() {
        return isRecording;
    }
    
    public interface AsrCallback {
        void onConnected();
        void onDisconnected();
        void onTranscriptionResult(String text, double confidence);
        void onBinaryMessage(byte[] data);
        void onError(Exception e);
    }
}