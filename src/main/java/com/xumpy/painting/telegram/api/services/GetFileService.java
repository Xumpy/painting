package com.xumpy.painting.telegram.api.services;

import com.xumpy.painting.telegram.api.model.GetFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.VideoNote;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetFileService {
    private String botToken = System.getProperty("telegram.bot.token");

    @Autowired
    private RestTemplate restTemplate;

    private String buildURLGetFile(String fileId) {
        String base = "https://api.telegram.org/bot" + botToken;
        String url = base + "/getFile?file_id=" + fileId;

        return url;
    }

    private String buildUrlGetFileContent(String filePath) {
        String base = "https://api.telegram.org/file/bot" + botToken;
        String url = base + "/" + filePath;

        return url;
    }

    private GetFile getLargestFile(List<GetFile> getFiles) {
        Integer largestFileSize = 0;
        GetFile largestFile = new GetFile();
        for (GetFile getFile : getFiles) {
            if (getFile.getResult().getFile_size() > largestFileSize) {
                largestFile = getFile;
            }
        }
        return largestFile;
    }

    public byte[] getPhoto(List<PhotoSize> photoSizes) {
        List<GetFile> getFiles = new ArrayList<>();

        for (PhotoSize photoSize : photoSizes) {
            getFiles.add(restTemplate.getForObject(
                    buildURLGetFile(photoSize.getFileId()), GetFile.class));
        }
        GetFile getFile = getLargestFile(getFiles);

        byte[] imageBytes = restTemplate.getForObject(
                buildUrlGetFileContent(getFile.getResult().getFile_path()), byte[].class);

        return imageBytes;
    }

    public byte[] getVideo(Video video) {
        GetFile getFile = restTemplate.getForObject(
                buildURLGetFile(video.getFileId()), GetFile.class);
        byte[] imageBytes = restTemplate.getForObject(
                buildUrlGetFileContent(getFile.getResult().getFile_path()), byte[].class);

        return imageBytes;
    }

    public byte[] getVideoNote(VideoNote videoNote){
        GetFile getFile = restTemplate.getForObject(
                buildURLGetFile(videoNote.getFileId()), GetFile.class);
        byte[] imageBytes = restTemplate.getForObject(
                buildUrlGetFileContent(getFile.getResult().getFile_path()), byte[].class);

        return imageBytes;
    }
}
