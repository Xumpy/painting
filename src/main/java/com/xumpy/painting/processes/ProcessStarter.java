package com.xumpy.painting.processes;

public abstract class ProcessStarter {
    public abstract ProcessBuilder processBuilder();

    public void start(){
        try {
            Process p = processBuilder().start();
            p.waitFor();
            p.destroy();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
