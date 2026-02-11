package com.example.historicquote.service;

import com.example.historicquote.model.DailyQuote;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HistoryService {

    private final Map<LocalDate, DailyQuote> store = new LinkedHashMap<>();

    public void save(DailyQuote quote) {
        store.put(quote.date(), quote);
    }

    public DailyQuote get(LocalDate date) {
        return store.get(date);
    }

    public Map<LocalDate, DailyQuote> all() {
        return store;
    }
}
