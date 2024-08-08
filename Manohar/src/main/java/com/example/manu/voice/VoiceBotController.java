package com.example.manu.voice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/voicebot")
public class VoiceBotController {

    @Autowired
    private VoiceBotSDK voiceBotSDK;

    @PostMapping("/setup")
    public String setup(
            @RequestParam String sttEngine,
            @RequestParam String ttsEngine,
            @RequestParam String llmEngine,
            @RequestParam String systemPrompt) {
        voiceBotSDK.setup(sttEngine, ttsEngine, llmEngine, systemPrompt);
        return "Voice bot SDK setup completed.";
    }

    @PostMapping("/stream")
    public ResponseEntity<String> streamConversation(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            voiceBotSDK.streamConversation(inputStream, outputStream);

            return ResponseEntity.ok("Streaming completed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the stream: " + e.getMessage());
        }
    }
}
