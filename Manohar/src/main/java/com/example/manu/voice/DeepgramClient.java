// DeepgramClient.java
package com.example.manu.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DeepgramClient {

    @Autowired
    private Config apiConfig;

    public String convertSpeechToText(InputStream audioStream) throws IOException {
        String apiKey = apiConfig.getDeepgramApiKey();
        URL url = new URL("https://api.deepgram.com/v1/listen");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Token " + apiKey);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "audio/wav");

        try (var outputStream = connection.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = audioStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        return response.toString(); // Extract text from response as needed
    }
}
