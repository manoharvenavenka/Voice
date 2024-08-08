package com.example.manu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${deepgram.api.key}")
    private String deepgramApiKey;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    public String getDeepgramApiKey() {
        return deepgramApiKey;
    }

    public String getOpenaiApiKey() {
        return openaiApiKey;
    }
}
