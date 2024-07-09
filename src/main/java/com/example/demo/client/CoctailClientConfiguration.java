package com.example.demo.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CoctailClientConfiguration {
    @Bean
    public CoctailClient coctailClient(@Value("${cocktail.api.url}") final String coctailApiUrl) {
        final RestClient restClient = RestClient.builder().baseUrl(coctailApiUrl).build();
        final RestClientAdapter adapter = RestClientAdapter.create(restClient);
        final HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(CoctailClient.class);
    }
}