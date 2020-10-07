package com.xumpy.painting.telegram.component;

import com.xumpy.painting.database.FileDB;
import com.xumpy.painting.database.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
public class FileBuilder {

    @Autowired private FileDB fileDB;

    private void printByteArrayAsBase64(byte[] byteArray){
        byte[] encoded = Base64.getEncoder().encode(byteArray);
        System.out.println(new String(encoded));
    }

    private void writeFileToFileSystem(byte[] byteArray, String fileName){
        try{
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(byteArray);
            outputStream.close();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public String build(byte[] byteArray, FileType fileType){
        Number rowId = fileDB.insertFile("/home/pi/" + fileType.getType(), fileType.getType());
        Files file = fileDB.selectFile(rowId);

        String fileName = file.getLocation_base() + "/" + file.getRowId() + "." + file.getFile_type();
        writeFileToFileSystem(byteArray, fileName);

        return fileName;
    }
}
