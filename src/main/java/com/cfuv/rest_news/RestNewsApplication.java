package com.cfuv.rest_news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class RestNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestNewsApplication.class, args);
    }

}
