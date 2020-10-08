package com.xumpy.painting.telegram;

import com.xumpy.painting.telegram.component.PaintingAtTheWallComponent;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PaintingAtTheWallBot extends TelegramLongPollingBot {
    private PaintingAtTheWallComponent paintingAtTheWallComponent;

    public PaintingAtTheWallBot(PaintingAtTheWallComponent paintingAtTheWallComponent){
        this.paintingAtTheWallComponent = paintingAtTheWallComponent;
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotApiMethod method = null;
        try{
            if (update.getMessage() != null){
                method = paintingAtTheWallComponent.message(update.getMessage());
            } else if (update.getCallbackQuery() != null){
                method = paintingAtTheWallComponent.callBackQuery(update.getCallbackQuery());
            }


            if (method != null){
                execute(method);
            }
        } catch(TelegramApiException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String getBotUsername() {
        return System.getProperty("telegram.bot.username");
    }

    @Override
    public String getBotToken() {
        return System.getProperty("telegram.bot.token");
    }
}
