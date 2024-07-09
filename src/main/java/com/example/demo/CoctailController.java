package com.example.demo;


import com.example.demo.client.CoctailClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("coctails")
public class CoctailController {
    private final CoctailClient coctailClient;

    public CoctailController(final CoctailClient coctailClient) {
        this.coctailClient = coctailClient;
    }

    @GetMapping()
    public CocktailResponse getWeather(@RequestParam String coctailName) {
        return coctailClient.getCoctail(coctailName);
    }
}