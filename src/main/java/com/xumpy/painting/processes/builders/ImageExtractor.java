package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class ImageExtractor implements ProcessStarter {

    ProcessBuilder pb = new ProcessBuilder();

    public ImageExtractor(String movieFile, String imageFile){
        pb.command("/usr/bin/ffmpeg", "-i", movieFile, "-vf", "select=eq(n,0)", "-vf", "scale=-2:900", "-q:v", "3", "-frames:v", "1", imageFile);
    }

    @Override
    public void start() throws IOException, InterruptedException {
        Process p = pb.start();;
        String result = "";
        final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
        reader.lines().iterator().forEachRemaining(sj::add);
        result = sj.toString();

        p.waitFor();
        p.destroy();

        System.out.println(result);
    }
}
