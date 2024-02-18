package com.example.emailsgenerator.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

  @AllArgsConstructor 
  @Getter
       
    @Setter
    @NoArgsConstructor
public class ChatResponse {
    private List<Choice> choices;

  

    // constructors, getters and setters
    
    @Setter
    @Getter
    @AllArgsConstructor
    public static class Choice {

        private int index;
        private Message message;

       
    }
}
