package com.xumpy.painting.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PaintingAtTheWallBot extends TelegramLongPollingBot {
    private String userName;
    private String botToken;

    public PaintingAtTheWallBot(String userName, String botToken){
        this.userName = userName;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasPhoto()){
            System.out.println("to be implemented");
        }
        if (update.getMessage().hasVideo()){
            System.out.println("to be implemented");
        }
        if (update.getMessage().hasText()){
            System.out.println(update.getMessage().getText());
            System.out.println("to be implemented");
        }

        for (PhotoSize photoSize: update.getMessage().getPhoto()){
            System.out.println(photoSize.getFileSize() + ": " + photoSize.getFileId());
        }
    }

    @Override
    public String getBotUsername() {
        return this.userName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }
}
