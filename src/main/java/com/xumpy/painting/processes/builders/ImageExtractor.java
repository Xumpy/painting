package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

import java.io.IOException;

public class ImageExtractor implements ProcessStarter {

    ProcessBuilder pb = new ProcessBuilder();

    public ImageExtractor(String movieFile, String imageFile){
        pb.command("bash", "-c", "/usr/bin/ffmpeg", "-i", movieFile, "-vf", "select=eq(n,0)", "-vf", "scale=-2:900", "-q:v", "3", "-frames:v", "1", imageFile);
    }

    @Override
    public void start() throws IOException {
        pb.start();
    }
}
