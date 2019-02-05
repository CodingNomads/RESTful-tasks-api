package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by ryandesmond on 7/30/18.
 */

@SpringBootApplication
//@EnableJpaAuditing
public class TasksApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TasksApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(TasksApp.class);
    }

}
