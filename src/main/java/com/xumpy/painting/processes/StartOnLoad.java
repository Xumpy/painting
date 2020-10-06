package com.xumpy.painting.processes;

import com.xumpy.painting.processes.builders.ImageExtractor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StartOnLoad {

    public StartOnLoad() throws IOException {
        ProcessStarter processStarter = new ImageExtractor("/home/pi/ChildScare.mp4", "/home/pi/output.jpeg");

        processStarter.start();
    }

}
