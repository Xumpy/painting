package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

public class FrameLoader extends ProcessStarter {
    private ProcessBuilder pb = new ProcessBuilder();

    public FrameLoader(String imageFile){

    }

    @Override
    public ProcessBuilder processBuilder() {
        return pb;
    }
}
