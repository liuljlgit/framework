package com.cloud.frame.framesecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class ResourceTokenConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean(name = "tokenStore")
    @ConditionalOnMissingBean
    public TokenStore getRedisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

}
