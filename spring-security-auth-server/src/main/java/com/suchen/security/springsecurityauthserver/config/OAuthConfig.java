package com.suchen.security.springsecurityauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Configuration
    public class JwtTokenStoreConfig {


        /**
         * 将access token 转化为jwt, 授权服务器和资源服务器使用同一个，保证key相同
         *
         * @return
         */
        @Bean
        public JwtTokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            //设置jwt密钥
            jwtAccessTokenConverter.setSigningKey("test_keytest_keytest_keytest_key");
            // jwtAccessTokenConverter.setKeyPair();
            return jwtAccessTokenConverter;
        }

//        @Bean
//        public JwtTokenEnhancer jwtTokenEnhancer() {
//            return new JwtTokenEnhancer();
//        }
    }

    public static void main(String[] args) {
        byte[] bytes = "test_key".getBytes();
        String key = new String(bytes);
        System.out.println(key);
    }

}
