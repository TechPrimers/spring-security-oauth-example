package com.suchen.security.springsecurityauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringSecurityAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAuthServerApplication.class, args);
    }
}
