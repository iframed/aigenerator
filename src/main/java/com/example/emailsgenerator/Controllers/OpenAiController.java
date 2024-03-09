package com.example.emailsgenerator.Controllers;


import java.util.Map;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;




@CrossOrigin
@RestController
public class OpenAiController {
   
   @Value("${spring.ai.openai.api-key}")
   private String apiKey;



    @Autowired
    private ChatClient chatClient;
    
    @GetMapping("/chatai")
    public String chat(String message){
        String response = chatClient.call(message);
        return response;
    }

   
    @GetMapping("/emails")
    public ResponseEntity<Object>  emails(@RequestParam(name = "category", defaultValue = "" ) String category ,
                         @RequestParam(name ="destinataire", defaultValue = "") String destinataire,
                         @RequestParam(name ="subject", defaultValue = " ") String subject) throws JsonProcessingException {
        
       
                     OpenAiApi openAiApi = new OpenAiApi(apiKey)   ;
                     OpenAiChatOptions options = OpenAiChatOptions.builder()
                     .withModel("gpt-3.5-turbo")
                     .withTemperature(0F)
                     .withMaxTokens(2000)
                     .build();
                     
                     OpenAiChatClient openAiChatClient = new OpenAiChatClient(openAiApi , options);
                     SystemPromptTemplate  promptTemplate = new SystemPromptTemplate( """
                        I need you to write an email regarding the following:
                        Subject: {subject}
                        Category: {category}
                        Destinataire: {destinataire}
                        Please ensure that the email content is tailored to address the following points:
                        - Subject: {subject}
                        - Category: {category}
                        - Destinataire: {destinataire}
                        The email should be formatted in JSON.
                             """
                        
                    )   ;  
                           
                    Prompt prompt = promptTemplate.create(Map.of("category", category, "destinataire",destinataire ,"subject",subject));
                   
                    
                  ChatResponse response =  openAiChatClient.call(prompt);
                    
    String content = response.getResult().getOutput().getContent();

    ObjectMapper objectMapper = new ObjectMapper();
    Object responseData = objectMapper.readValue(content, Object.class);

    return ResponseEntity.ok(responseData);

    }

} 
  /*Map<String,Object> , TypeReference<HashMap<String, Object>> typeReference = new TypeReference<>() {};
    return objectMapper.readValue(content, typeReference); */

