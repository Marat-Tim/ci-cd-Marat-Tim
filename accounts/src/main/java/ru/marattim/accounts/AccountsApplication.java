package ru.marattim.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountsApplication {
    public static void main(String[] args) {
        String dbHost = System.getenv("DB_HOST");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String dbUser = System.getenv("DB_USER");

        System.out.println("DB_HOST: " + dbHost);
        System.out.println("DB_PORT: " + dbPort);
        System.out.println("DB_NAME: " + dbName);
        System.out.println("DB_USER: " + dbUser);

        SpringApplication.run(AccountsApplication.class, args);
    }
}
