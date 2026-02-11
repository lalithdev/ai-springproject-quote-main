package com.example.historicquote.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class WikipediaOnThisDayService {

    private final RestClient restClient;

    public WikipediaOnThisDayService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://en.wikipedia.org/api/rest_v1")
                .defaultHeader(HttpHeaders.USER_AGENT,
                        "historic-quote-of-the-day (https://github.com/yourname/historic-quote-of-the-day)")
                .build();
    }

    public String fetchContext(LocalDate date) {
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String title = month + "_" + date.getDayOfMonth();

        WikipediaSummary summary = restClient.get()
                .uri("/page/summary/{title}", title)
                .retrieve()
                .body(WikipediaSummary.class);

        if (summary == null || summary.extract() == null || summary.extract().isBlank()) {
            return "Today is " + month + " " + date.getDayOfMonth() + ". Provide a timeless quote inspired by history and human progress.";
        }
        return summary.extract();
    }

    public record WikipediaSummary(String extract) {
    }
}
