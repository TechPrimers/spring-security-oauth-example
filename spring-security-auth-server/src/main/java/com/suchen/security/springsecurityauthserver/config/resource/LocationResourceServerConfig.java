package com.suchen.security.springsecurityauthserver.config.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;


/**
 * oAuth resource server
 * 只作用于/locations,/location/** 开头
 */
@Configuration
@Order(10)
public class LocationResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SecretKeySpec key = new SecretKeySpec("test_keytest_keytest_keytest_key".getBytes(), "HMACSHA256");

        JwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(key).build();

        //拦截/user开头的所有请求
        http.requestMatchers().antMatchers("/locations", "/locations/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt().decoder(jwtDecoder);
    }
}
