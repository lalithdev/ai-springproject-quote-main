package com.example.historicquote.service;

import com.example.historicquote.model.DailyQuote;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DailyQuoteScheduler {

    private final WikipediaOnThisDayService wikipediaService;
    private final QuoteGeneratorService generatorService;
    private final HistoryService historyService;

    public DailyQuoteScheduler(WikipediaOnThisDayService wikipediaService,
                              QuoteGeneratorService generatorService,
                              HistoryService historyService) {
        this.wikipediaService = wikipediaService;
        this.generatorService = generatorService;
        this.historyService = historyService;
    }

    @PostConstruct
    public void generateOnStartup() {
        generateAndStore(LocalDate.now());
    }

    @Scheduled(cron = "${quote.schedule.cron:0 0 8 * * *}")
    public void generateDaily() {
        generateAndStore(LocalDate.now());
    }

    private void generateAndStore(LocalDate date) {
        String context = wikipediaService.fetchContext(date);
        String quote = generatorService.generateQuote(context);

        DailyQuote dailyQuote = new DailyQuote(
                date,
                context,
                quote,
                "AI-generated using Spring AI ChatClient + local Ollama; context from Wikipedia date page summary."
        );

        historyService.save(dailyQuote);

        System.out.println("=== Quote of the Day (" + date + ") ===");
        System.out.println(quote);
        System.out.println("=====================================");
    }
}
