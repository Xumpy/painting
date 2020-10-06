package com.xumpy.painting.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class PaintingAtTheWallComponent {
    private String telegramBotUsername = System.getProperty("telegram.bot.username");
    private String telegramBotToken = System.getProperty("telegram.bot.token");

    public PaintingAtTheWallComponent(){
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new PaintingAtTheWallBot(telegramBotUsername, telegramBotToken));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
