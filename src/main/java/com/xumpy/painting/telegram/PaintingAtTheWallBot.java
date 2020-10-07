package com.xumpy.painting.telegram;

import com.xumpy.painting.processes.ProcessStarter;
import com.xumpy.painting.processes.builders.FrameLoader;
import com.xumpy.painting.telegram.api.services.RestService;
import com.xumpy.painting.telegram.component.FileBuilder;
import com.xumpy.painting.telegram.component.FileType;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PaintingAtTheWallBot extends TelegramLongPollingBot {
    private String userName;
    private String botToken;
    private RestService restService;
    private FileBuilder fileBuilder;

    public PaintingAtTheWallBot(String userName, String botToken, FileBuilder fileBuilder, RestService restService){
        this.userName = userName;
        this.botToken = botToken;
        this.restService = restService;
        this.fileBuilder = fileBuilder;
    }

    private void SendReciveidFile(Message message, String fileName){
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Received and saved file: " + fileName);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage().hasPhoto()){
            byte[] photo = restService.getPhoto(update.getMessage().getPhoto());
            String fileName = fileBuilder.build(photo, FileType.JPEG);

            SendReciveidFile(update.getMessage(), fileName);

            ProcessStarter frameLoader = new FrameLoader(fileName);
            frameLoader.start();
        }
        if (update.getMessage().hasVideo()){
            byte[] video = restService.getVideo(update.getMessage().getVideo());
            String fileName = fileBuilder.build(video, FileType.AVI);

            SendReciveidFile(update.getMessage(), fileName);

            System.out.println("to be implemented");
        }
        if (update.getMessage().hasVideoNote()){
            byte[] video = restService.getVideoNote(update.getMessage().getVideoNote());
            String fileName = fileBuilder.build(video, FileType.AVI);

            SendReciveidFile(update.getMessage(), fileName);

            System.out.println("to be implemented");
        }
        if (update.getMessage().hasText()){
            System.out.println(update.getMessage().getText());
            System.out.println("to be implemented");
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
