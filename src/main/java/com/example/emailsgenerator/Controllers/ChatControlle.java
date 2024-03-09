package com.example.emailsgenerator.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.emailsgenerator.Dto.ChatRequest;
import com.example.emailsgenerator.Dto.ChatResponse;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class ChatControlle {
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @GetMapping("/chat")

     

    public ResponseEntity<ChatResponse> chat(@RequestParam String prompt) {
        // Créer la requête
        ChatRequest request = new ChatRequest(model, prompt);
    
        // Appeler l'API OpenAI
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
    
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
    
        return ResponseEntity.ok(response);
    }
}
