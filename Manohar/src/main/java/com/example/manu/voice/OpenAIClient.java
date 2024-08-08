package com.example.manu.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;

@Component
public class OpenAIClient {

    @Autowired
    private Config apiConfig;

    public String queryLLM(String prompt) throws IOException {
        String apiKey = apiConfig.getOpenAiApiKey();
        URL url = new URL("https://api.openai.com/v1/engines/davinci/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String payload = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":50}";
        try (var outputStream = connection.getOutputStream()) {
            outputStream.write(payload.getBytes());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        return response.toString(); 
    }

    public void convertTextToSpeech(String text, OutputStream outputStream) throws IOException {
        
    }
}
