// VoiceBotSDKImpl.java
package com.example.manu.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class VoiceBotSDKImpl implements VoiceBotSDK {

    @Autowired
    private DeepgramClient deepgramClient;

    @Autowired
    private OpenAIClient openAIClient;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private String systemPrompt;

    @Override
    public void setup(String sttEngine, String ttsEngine, String llmEngine, String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    @Override
    public void streamConversation(InputStream inputStream, OutputStream outputStream) {
        CompletableFuture.runAsync(() -> {
            try {
                String text = deepgramClient.convertSpeechToText(inputStream);
                String responseText = openAIClient.queryLLM(systemPrompt + " " + text);
                openAIClient.convertTextToSpeech(responseText, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executorService);
    }
}
