package com.example.manu.voice;

import java.io.InputStream;
import java.io.OutputStream;

public interface VoiceBotSDK {
    void setup(String sttEngine, String ttsEngine, String llmEngine, String systemPrompt);
    void streamConversation(InputStream inputStream, OutputStream outputStream);
}
