package ru.marattim.converter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RatesClientConfig {
    @Bean
    public WebClient getWebClient(@Value("${rates.url}") String ratesUrl) {
        return WebClient.create(ratesUrl);
    }
}
