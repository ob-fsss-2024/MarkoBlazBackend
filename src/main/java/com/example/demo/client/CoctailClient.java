package com.example.demo.client;

import com.example.demo.Cocktail;
import com.example.demo.CocktailResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface CoctailClient {
    @GetExchange("/search.php?s={s}")
    CocktailResponse getCoctail(@PathVariable("s") String s);

    @GetExchange("/lookup.php?i={cocktailid}")
    CocktailResponse getCocktailbyId(@PathVariable String cocktailid);
}


