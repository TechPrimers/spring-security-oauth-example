package com.techprimers.security.springsecurityauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 通用的安全配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
//                .and()
//                    .formLogin().permitAll()
                .and()
                    .httpBasic()
                .and()
                    .csrf().disable();
    }

    /**
     * 注册AuthenticationManager bean,通过父类中的方法来创建AuthenticationManager实例
     * authenticationBuilder是最终的builder
     * localConfigureAuthenticationBldr 是提供给开发人员扩展的
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 设置AuthenticationManagerBuilder，添加一个内存验证的provider
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Peter")
                .password(passwordEncoder.encode("peter"))
                .roles("USER");
    }
}
