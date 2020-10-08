package com.xumpy.painting.telegram;

import com.xumpy.painting.telegram.component.PaintingAtTheWallComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class PaintingAtTheWallLoader implements CommandLineRunner {
    @Autowired
    private PaintingAtTheWallComponent paintingAtTheWallComponent;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new PaintingAtTheWallBot(paintingAtTheWallComponent));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
