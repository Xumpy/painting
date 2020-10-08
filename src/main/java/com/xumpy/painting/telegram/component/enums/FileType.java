package com.xumpy.painting.telegram.component.enums;

public enum FileType {
    JPEG("JPEG"),
    AVI("AVI"),
    MP4("MP4"),
    PNG("PNG");

    private String fileType;
    public String getType(){
        return fileType;
    }

    FileType(String fileTYpe){
        this.fileType = fileTYpe;
    }
}
