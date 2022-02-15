package com.suchen.security.springsecurityauthserver.config;

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
 * 通用的安全配置,优先级最低
 * 作用于 / 开头
 */
@Configuration
//@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 父类中先创建HttpSecurity，然后再配置它
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                        .antMatchers("/actuator/**").permitAll()
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
     * 此处是localConfigureAuthenticationBldr 是提供给开发人员扩展的
     * authenticationBuilder是最终的builder
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
