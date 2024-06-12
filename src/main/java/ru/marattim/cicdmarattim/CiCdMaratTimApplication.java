package ru.marattim.cicdmarattim;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CiCdMaratTimApplication {

    @Bean
    public WebClient getWebClient(@Value("${rates.url}") String ratesUrl) {
        return WebClient.create(ratesUrl);
    }


    public static void main(String[] args) {
        SpringApplication.run(CiCdMaratTimApplication.class, args);
    }
}
