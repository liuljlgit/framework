package com.cloud.frame.frameauth.annotation.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:37
 */
public class TokenKeyPairAccessImpl {

    @Bean(name = "jwtAccessTokenConverter")
    @ConditionalOnMissingBean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource("oauth2-jwt.jks"), "123456".toCharArray())
                .getKeyPair("oauth2-jwt");
        accessTokenConverter.setKeyPair(keyPair);
        return accessTokenConverter;
    }
}
