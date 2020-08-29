package com.cloud.frame.framesecurity.config;

import com.cloud.frame.framesecurity.annotation.EnableTokenPublicKeyAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableTokenPublicKeyAccess
public class ResourceTokenConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean(name = "tokenStore")
    @ConditionalOnMissingBean
    public TokenStore getJwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

//    @Bean(name = "tokenStore")
//    @ConditionalOnMissingBean
//    public TokenStore getRedisTokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }

}
