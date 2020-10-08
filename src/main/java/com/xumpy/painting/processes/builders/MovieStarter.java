package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

import java.util.Arrays;
import java.util.List;

public class MovieStarter extends ProcessStarter {
    private String fileName;

    public MovieStarter(String fileName){
        this.fileName = fileName;
    }

    @Override
    public List<String> processCommands() {
        return Arrays.asList("/usr/bin/omxplayer", "-o", "both", "--win", "0 801 1440 900", "--aspect-mode", "fill", "--no-osd", "--vol", "-600", fileName);
    }
}
