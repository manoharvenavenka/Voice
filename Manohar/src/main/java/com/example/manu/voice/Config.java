// Config.java
package com.example.manu.voice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    
    @Value("${deepgram.api.key}")
    private String deepgramApiKey;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    public String getDeepgramApiKey() {
        return deepgramApiKey;
    }

    public String getOpenAiApiKey() {
        return openAiApiKey;
    }
}
