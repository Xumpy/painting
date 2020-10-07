package com.xumpy.painting.telegram.component;

public enum FileType {
    JPEG("JPEG"),
    AVI("AVI");

    private String fileType;
    public String getType(){
        return fileType;
    }

    FileType(String fileTYpe){
        this.fileType = fileTYpe;
    }
}
