package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

import java.util.Arrays;
import java.util.List;

public class FrameLoader extends ProcessStarter {
    private String imageFile;

    public FrameLoader(String imageFile){
        this.imageFile = imageFile;
    }

    @Override
    public List<String> processCommands() {
        return Arrays.asList("sudo", "bash", "-c", "fbi -T 1 -d /dev/fb0 -noverbose -once " + imageFile);
    }
}
