package ru.marattim.cicdmarattim;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
@Log4j2
public class CiCdMaratTimApplication {
    @Bean
    Random getRandom() {
        return new Random();
    }

    // Можно было написать сервис для хранения этого числа, но кажется это перебор для такого приложения
    @Bean
    int getRandomInt(Random random) {
        int randomInt = random.nextInt();
        log.info("Generate random number {}", randomInt);
        return randomInt;
    }

    public static void main(String[] args) {
        SpringApplication.run(CiCdMaratTimApplication.class, args);
    }
}
