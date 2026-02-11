package com.example.historicquote.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class QuoteGeneratorService {

    private final ChatClient chatClient;

    public QuoteGeneratorService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String generateQuote(String context) {
        String prompt = """
                Create a short, original "Quote of the Day" inspired by the historical context below.

                Rules:
                - 1–2 lines max
                - inspirational and respectful
                - do NOT claim it is a real historical quote
                - do NOT mention politics or real persons
                - output ONLY the quote text, nothing else

                Historical context:
                %s
                """.formatted(context);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
