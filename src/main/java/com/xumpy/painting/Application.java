package com.xumpy.painting;

import com.xumpy.painting.processes.ProcessStarter;
import com.xumpy.painting.processes.builders.ImageExtractor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public void commandLineRunner() throws IOException {
        ProcessStarter processStarter = new ImageExtractor("/home/pi/ChildScare.mp4", "/home/pi/output.jpeg");

        processStarter.start();
    }
}
