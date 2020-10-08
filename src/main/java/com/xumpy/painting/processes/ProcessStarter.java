package com.xumpy.painting.processes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class ProcessStarter {
    public abstract List<String> processCommands();
    private String profile = System.getProperty("spring.profiles.active");

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessStarter.class);

    private void executeProcess(){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(processCommands());
            Process p = processBuilder.start();
            p.waitFor();
            p.destroy();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void printProcess(){
        String output = new String();
        for(String command: processCommands()){
            output = output + " " + command;
        }
        if (profile.equals("pi")){
            LOGGER.info("Executed on " + profile + ": "  + output);
        } else {
            LOGGER.info("Mocked on " + profile + ": "  + output);
        }
    }

    public void start(){
        if (profile.equals("pi")){
            executeProcess();
        }
        printProcess();
    }
}
