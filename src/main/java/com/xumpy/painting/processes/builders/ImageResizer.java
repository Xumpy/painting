package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

import java.util.Arrays;
import java.util.List;

public class ImageResizer extends ProcessStarter {
    private static final String RESIZE="1440x900^";
    private String imageFile;

    public ImageResizer(String imageFile){
        this.imageFile = imageFile;
    }

    @Override
    public List<String> processCommands() {
        return Arrays.asList("/usr/bin/convert", imageFile, "-resize", RESIZE, imageFile);
    }
}
