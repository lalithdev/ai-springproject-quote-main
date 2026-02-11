package com.example.historicquote.controller;

import com.example.historicquote.model.DailyQuote;
import com.example.historicquote.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final HistoryService historyService;

    public QuoteController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/today")
    public DailyQuote today() {
        LocalDate today = LocalDate.now();
        DailyQuote q = historyService.get(today);
        return (q != null) ? q : new DailyQuote(today, "", "Not generated yet.", "Wait for startup or scheduler.");
    }

    @GetMapping("/history")
    public Map<LocalDate, DailyQuote> history() {
        return historyService.all();
       
    }
}
