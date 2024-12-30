package com.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstAiApplication {

    @Value("MY_APP_KEY")
    private String myKey;

    public static void main(String[] args) {
        SpringApplication.run(FirstAiApplication.class, args);
    }

}
