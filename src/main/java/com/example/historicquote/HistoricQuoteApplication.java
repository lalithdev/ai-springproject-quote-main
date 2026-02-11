package com.example.historicquote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HistoricQuoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(HistoricQuoteApplication.class, args);
    }
}
