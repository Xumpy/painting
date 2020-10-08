package com.xumpy.painting.telegram.component;

import com.xumpy.painting.database.FileDB;
import com.xumpy.painting.database.model.Files;
import com.xumpy.painting.processes.builders.ImageResizer;
import com.xumpy.painting.telegram.component.enums.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class FileBuilderComponent {

    @Autowired private FileDB fileDB;

    @Value("${home.folder}")
    private String homeFolder;

    private void writeFileToFileSystem(byte[] byteArray, String fileName){
        try{
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(byteArray);
            outputStream.close();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public String loadScareFirstFrame(String scareFile){
        return homeFolder + "/" + FileType.PNG + "/" + scareFile.toLowerCase() + "." + FileType.PNG;
    }

    public String loadScareRandomMovie(String scareFile){
        List<Integer> givenList = Arrays.asList(1, 2, 3);
        Random rand = new Random();
        String fileName = scareFile + givenList.get(rand.nextInt(givenList.size()));

        return homeFolder + "/" + FileType.MP4 + "/" + fileName.toLowerCase() + "." + FileType.MP4;
    }

    public String fileName(Files file){
        return file.getLocation_base() + "/" + file.getRowId() + "." + file.getFile_type();
    }

    public String fileName(Integer rowId){
        return fileName(fileDB.selectFile(rowId));
    }

    public String build(byte[] byteArray, FileType fileType){
        Number rowId = fileDB.insertFile(homeFolder + "/" + fileType.getType(), fileType.getType());
        Files file = fileDB.selectFile(rowId);

        writeFileToFileSystem(byteArray, fileName(file));
        new ImageResizer(fileName(file)).start();

        return fileName(file);
    }
}
