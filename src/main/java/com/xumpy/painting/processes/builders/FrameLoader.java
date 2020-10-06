package com.xumpy.painting.processes.builders;

import com.xumpy.painting.processes.ProcessStarter;

public class FrameLoader extends ProcessStarter {
    @Override
    public ProcessBuilder processBuilder() {
        return new ProcessBuilder();
    }
}
