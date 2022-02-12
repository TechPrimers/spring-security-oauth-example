package com.suchen.security.springsecurityauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
 * oAuth resource server
 * 只作用于/user/ 开头
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //spring secuity提供了requestMatchers接口，等价于http.authorizeRequests().anyRequest().access("permitAll");
        //提供资源，访问/user需要权限认证
        //拦截/user开头的所有请求
        http.requestMatchers().antMatchers("/users", "/users/**", "/user/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}
