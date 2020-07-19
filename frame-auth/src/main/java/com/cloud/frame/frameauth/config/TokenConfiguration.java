package com.cloud.frame.frameauth.config;

import com.cloud.frame.frameauth.annotation.EnableTokenKeyPairAccess;
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

    //可以使用redis存储token,资源服务器可以使用redis或者token进行校验，都是可以的
    @Bean(name = "tokenStore")
    public TokenStore getTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    //使用这种方式每次生成的token都是不一样的
//    @Bean(name = "tokenStore")
//    public TokenStore getTokenStore(){
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }

    @Bean(name = "jwtTokenEnhancer")
    @ConditionalOnMissingBean( name = "jwtTokenEnhancer")
    public TokenEnhancer tokenEnhancer(){
        return new SecurityTokenEnhancer();
    }

    @Primary
    @Bean("cloudTokenServices")
    public DefaultTokenServices createDefaultTokenServices(ClientDetailsService clientDetailsService) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(getTokenStore());
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
