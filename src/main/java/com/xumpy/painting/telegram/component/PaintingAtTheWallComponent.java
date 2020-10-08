package com.xumpy.painting.telegram.component;

import com.xumpy.painting.processes.builders.FrameLoader;
import com.xumpy.painting.processes.builders.MovieStarter;
import com.xumpy.painting.telegram.api.services.GetFileService;
import com.xumpy.painting.telegram.component.enums.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class PaintingAtTheWallComponent {
    @Autowired private FileBuilderComponent fileBuilderComponent;
    @Autowired private GetFileService getFileService;

    @Value("${home.folder}")
    private String homeFolder;

    private BotApiMethod sendMessage(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);

        return sendMessage;
    }

    private SendMessage createButton(SendMessage sendMessage, String buttonText, String buttonInternalName){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(buttonText).setCallbackData(buttonInternalName));
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }

    private BotApiMethod command(Message message){
        if (message.getText().toLowerCase().startsWith("load")){
            String[] commands = message.getText().split(" ");
            new FrameLoader(fileBuilderComponent.fileName(Integer.parseInt(commands[1]))).start();
        }

        if (message.getText().toLowerCase().startsWith("scare")) {

            String[] commands = message.getText().split(" ");
            String scareMode = commands[1];

            SendMessage sendMessage = new SendMessage()
                    .setChatId(message.getChatId())
                    .setText("Scare mode activated: " + scareMode);
            new FrameLoader(fileBuilderComponent.loadScareFirstFrame(scareMode)).start();

            return createButton(sendMessage, "Scare Someone", "scare_someone");
        }

        return null;
    }

    public BotApiMethod message(Message message){
        if (message.hasPhoto()){
            byte[] photo = getFileService.getPhoto(message.getPhoto());
            String fileName = fileBuilderComponent.build(photo, FileType.JPEG);

            new FrameLoader(fileName).start();

            return sendMessage(message, "Received and saved file: " + fileName);
        }
        if (message.hasVideo()){
            byte[] video = getFileService.getVideo(message.getVideo());
            String fileName = fileBuilderComponent.build(video, FileType.AVI);

            System.out.println("to be implemented");

            return sendMessage(message, "Received and saved file: " + fileName);
        }
        if (message.hasVideoNote()){
            byte[] video = getFileService.getVideoNote(message.getVideoNote());
            String fileName = fileBuilderComponent.build(video, FileType.AVI);

            System.out.println("to be implemented");

            return sendMessage(message, "Received and saved file: " + fileName);
        }
        if (message.hasText()){
            return command(message);
        }
        return null;
    }

    public BotApiMethod callBackQuery(CallbackQuery callbackQuery){
        if (callbackQuery.getData().equals("scare_someone")){
            String scareMode = callbackQuery.getMessage().getText().replace("Scare mode activated: ", "");

            new MovieStarter(fileBuilderComponent.loadScareRandomMovie(scareMode)).start();
        }
        return null;
    }
}
