package com.xumpy.painting.telegram.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

import java.util.List;

@Service
public class RestService {
    @Autowired GetFileService getFileService;

    public byte[] getPhoto(List<PhotoSize> photoSizes){
        return getFileService.getPhoto(photoSizes);
    }
    public byte[] getVideo(Video video){
        return getFileService.getVideo(video);
    }
    public byte[] getVideoNote(VideoNote videoNote){
        return getFileService.getVideoNote(videoNote);
    }
}
