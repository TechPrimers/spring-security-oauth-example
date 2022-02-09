package com.techprimers.security.springsecurityauthserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * oAuth authorize server
 */
@Configuration
//导入了FrameworkEndpointHandlerMapping，创建token相关的endpoint
//导入了AuthorizationServerSecurityConfiguration，继承自WebSecurityConfigurerAdapter
// 内部只拦截"/oauth/token"， "/oauth/token_key" ， "/oauth/check_token" 请求
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("ClientId")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("authorization_code", "password", "client_credentials") //authorization_code
                .redirectUris("http://localhost:9001/callback")
                .scopes("user_info")
                .autoApprove(true);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                //accessToken转JwtToken
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter);
//                //jwt内容增强
//                .tokenEnhancer(tokenEnhancerChain);
    }
}
