package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

public class ImageResizer extends ProcessStarter {
    private static final String RESIZE="1440x900^";

    private ProcessBuilder pb = new ProcessBuilder();


    public ImageResizer(String imageFile){
        pb.command("/usr/bin/convert", imageFile, "-resize", RESIZE, imageFile);
    }

    @Override
    public ProcessBuilder processBuilder() {
        return pb;
    }
}
