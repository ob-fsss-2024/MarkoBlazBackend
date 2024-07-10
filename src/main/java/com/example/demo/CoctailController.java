package com.example.demo;

import com.example.demo.client.CoctailClient;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("coctails")
public class CoctailController {
    private final CoctailClient coctailClient;
    private final AzureOpenAiChatModel chatModel;

    @Autowired
    public CoctailController(final CoctailClient coctailClient, final AzureOpenAiChatModel chatModel) {
        this.coctailClient = coctailClient;
        this.chatModel = chatModel;
    }

    @GetMapping()
    public CocktailResponse getCoctail(@RequestParam String coctailName) {
        return coctailClient.getCoctail(coctailName);
    }

    @GetMapping("/funfact")
    public Map<String, String> getCoctailFunFact(@RequestParam String coctailName) {
        String message = "Tell me a fun fact about the cocktail " + coctailName + ". Make it short.";
        return Map.of("funFact", chatModel.call(message));
    }
}
