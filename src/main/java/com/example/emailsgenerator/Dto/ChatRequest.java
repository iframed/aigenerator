package com.example.emailsgenerator.Dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));

        // Initialise les autres attributs avec des valeurs par défaut
        this.n = 1; // Initialise n à 1 par défaut
        this.temperature = 0.7; // Initialise temperature à 0.7 par défaut
    }

}
