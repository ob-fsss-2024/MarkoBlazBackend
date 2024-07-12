package com.example.demo;

import com.example.demo.client.CoctailClient;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(CoctailController.class.getName());
    private final Counter coctailRequestCounter;
    private final Counter funFactRequestCounter;
    private final Counter recipeRequestCounter;
    private final Counter cocktailbyidCounter;



    @Autowired
    public CoctailController(final CoctailClient coctailClient, final AzureOpenAiChatModel chatModel, MeterRegistry meterRegistry) {
        this.coctailClient = coctailClient;
        this.chatModel = chatModel;
        this.coctailRequestCounter = meterRegistry.counter("coctail_requests");
        this.funFactRequestCounter = meterRegistry.counter("fun_fact_requests");
        this.recipeRequestCounter = meterRegistry.counter("recipe_requests");
        this.cocktailbyidCounter = meterRegistry.counter("cocktailbyid_requests");
    }

    @GetMapping()
    public CocktailResponse getCoctail(@RequestParam String coctailName) {
        logger.info("Fetching cocktail details for {}", coctailName);
        coctailRequestCounter.increment();
        return coctailClient.getCoctail(coctailName);
    }

    @GetMapping("/funfact")
    public Map<String, String> getCoctailFunFact(@RequestParam String coctailName) {
        logger.info("Fetching fun fact about a cocktail {}", coctailName);
        funFactRequestCounter.increment();
        String message = "Tell me a fun fact about the cocktail " + coctailName + ". Make it short.";
        return Map.of("funFact", chatModel.call(message));
    }

    @GetMapping("/makemeacocktail")
    public Map<String, String> getCoctailRecipe(@RequestParam String myMood) {
        logger.info("Fetching the request for a recipe {}", myMood);
        recipeRequestCounter.increment();
        String message = "Give me a recipe for a cocktail based on my mood: " + myMood;
        return Map.of("moodRecipe", chatModel.call(message));
    }

    @GetMapping("/cocktailbyid")
    public CocktailResponse getCocktailById(@RequestParam String id) {
        logger.info("Fetching cocktail for id {}", id);
        cocktailbyidCounter.increment();
        return coctailClient.getCocktailbyId(id);
    }
}
