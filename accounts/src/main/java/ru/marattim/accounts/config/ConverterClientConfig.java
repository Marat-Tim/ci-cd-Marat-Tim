package ru.marattim.accounts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class ConverterClientConfig {
    @Bean
    public WebClient getWebClient(@Value("${converter.url}") String converterUrl,
                                  ClientRegistrationRepository clientRegistrationRepository,
                                  OAuth2AuthorizedClientRepository authorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);

        HttpClient client = HttpClient.create().responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(client))
                .apply(oauth2.oauth2Configuration())
                .baseUrl(converterUrl)
                .build();
    }
}