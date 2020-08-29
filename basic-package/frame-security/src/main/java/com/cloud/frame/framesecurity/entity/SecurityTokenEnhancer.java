package com.cloud.frame.framesecurity.entity;

import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:21
 */
public class SecurityTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> addtionalInfo = Maps.newHashMap();
        //token增强
//        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
//        addtionalInfo.put("userName",userDetails.getUsername());
//        addtionalInfo.put("roleName",userDetails.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));
        //加入自定义信息
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(addtionalInfo);
        return accessToken;
    }

}
