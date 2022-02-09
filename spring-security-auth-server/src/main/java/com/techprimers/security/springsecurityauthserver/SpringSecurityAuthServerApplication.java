package com.techprimers.security.springsecurityauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class SpringSecurityAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAuthServerApplication.class, args);
    }
}
