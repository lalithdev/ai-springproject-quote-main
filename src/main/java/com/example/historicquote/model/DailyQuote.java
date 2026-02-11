package com.example.historicquote.model;

import java.time.LocalDate;

public record DailyQuote(
        LocalDate date,
        String context,
        String quote,
        String note
) {}
