package com.example.manu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.manu.voice.VoiceBotSDK;

@SpringBootApplication
public class ManoharApplication {
	@Autowired
    private VoiceBotSDK voiceBotSDK;
	public static void main(String[] args) {
		SpringApplication.run(ManoharApplication.class, args);
	}
	   public void run(String... args) throws Exception {
	        voiceBotSDK.setup("deepgram", "openai", "openai", "This is the system prompt.");

	       
	    }
}
