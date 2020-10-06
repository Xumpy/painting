package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

public class ImageExtractor extends ProcessStarter {

    private ProcessBuilder pb = new ProcessBuilder();

    public ImageExtractor(String movieFile, String imageFile){
        pb.command("/usr/bin/ffmpeg", "-y", "-i", movieFile, "-vf", "select=eq(n,0)", "-vf", "scale=-2:900", "-q:v", "3", "-frames:v", "1", imageFile);
    }

    @Override
    public ProcessBuilder processBuilder() {
        return pb;
    }
}
