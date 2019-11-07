package com.cloud.frame.frameauth.controller;

import com.cloud.frame.authclient.feign.OauthFeign;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:44
 */
@Slf4j
@RestController
@Validated
@Api(tags = "自定义oauth接口")
public class OauthCtrl implements OauthFeign {

    @Autowired
    private DefaultTokenServices tokenServices;

    @GetMapping("/userinfo")
    public Authentication user(Authentication authentication) {
        return authentication;
    }

    @Override
    public Boolean loginOut(@RequestParam("token") String token) {
        return tokenServices.revokeToken(token);
    }
}
