package com.xumpy.painting.telegram;

import com.xumpy.painting.telegram.api.services.RestService;
import com.xumpy.painting.telegram.component.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class PaintingAtTheWallComponent implements CommandLineRunner {
    private String telegramBotUsername = System.getProperty("telegram.bot.username");
    private String telegramBotToken = System.getProperty("telegram.bot.token");

    @Autowired
    private RestService restService;

    @Autowired
    private FileBuilder fileBuilder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new PaintingAtTheWallBot(telegramBotUsername, telegramBotToken, fileBuilder, restService));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
