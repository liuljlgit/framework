package com.cloud.frame.frameauth.config;

import com.cloud.frame.framesecurity.annotation.EnableTokenKeyPairAccess;
import com.cloud.frame.framesecurity.entity.SecurityTokenEnhancer;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.List;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:05
 */
@Configuration
@EnableTokenKeyPairAccess
public class TokenConfiguration {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean(name = "tokenStore")
    public TokenStore getRedisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean(name = "jwtTokenEnhancer")
    @ConditionalOnMissingBean( name = "jwtTokenEnhancer")
    public TokenEnhancer tokenEnhancer(){
        return new SecurityTokenEnhancer();
    }

    @Primary
    @Bean("cloudTokenServices")
    public DefaultTokenServices createDefaultTokenServices(ClientDetailsService clientDetailsService) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(getRedisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(false);  //refresh使用是否使用第一次生成的
        tokenServices.setClientDetailsService(clientDetailsService);

        List<TokenEnhancer> enhancers = Lists.newArrayList();
        enhancers.add(tokenEnhancer());//这个必须在前面
        enhancers.add(jwtAccessTokenConverter);
        TokenEnhancerChain tokenEnhancer = new TokenEnhancerChain();
        tokenEnhancer.setTokenEnhancers(enhancers);
        tokenServices.setTokenEnhancer(tokenEnhancer);
        return tokenServices;
    }


}
