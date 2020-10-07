package com.xumpy.painting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS files(location_base VARCHAR(100), file_type VARCHAR(100))");
    }
}
