package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

import java.util.Arrays;
import java.util.List;

public class ImageExtractor extends ProcessStarter {

    private String movieFile;
    private String imageFile;

    public ImageExtractor(String movieFile, String imageFile){
        this.movieFile = movieFile;
        this.imageFile = imageFile;
    }

    @Override
    public List<String> processCommands() {
        return Arrays.asList("/usr/bin/ffmpeg", "-y", "-i", movieFile, "-vf", "select=eq(n,0)", "-vf", "scale=-2:900", "-q:v", "3", "-frames:v", "1", imageFile);
    }
}
