package com.cloud.frame.frameauth.controller;

import com.cloud.frame.authclient.config.LoginProperties;
import com.cloud.frame.authclient.entity.ComUser;
import com.cloud.frame.authclient.feign.OauthEnpointFeign;
import com.cloud.frame.authclient.feign.OauthFeign;
import com.cloud.frame.authclient.req.OauthTokenReq;
import com.cloud.frame.authclient.req.RegisterReq;
import com.cloud.frame.authclient.req.LoginReq;
import com.cloud.frame.authclient.resp.OauthTokenResp;
import com.cloud.frame.frameauth.service.IComUserService;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:44
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录注册等相关接口")
public class OauthCtrl implements OauthFeign {

    @Autowired
    OauthEnpointFeign oauthEnpointFeign;

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private IComUserService comUserService;


    @GetMapping("/checkuser")
    public Authentication user(Authentication authentication) {
        return authentication;
    }

    @Override
    public OauthTokenResp login(@RequestBody LoginReq loginReq) {
        OauthTokenReq oauthTokenReq = OauthTokenReq.builder()
                .applicationName(loginProperties.getApplicationName())
                .client_id(loginProperties.getClientId())
                .client_secret(loginProperties.getClientSecret())
                .grant_type(loginProperties.getGrantType())
                .password(loginReq.getPassword())
                .username(loginReq.getUsername())
                .scope(loginProperties.getScope())
                .build();
        return oauthEnpointFeign.login(oauthTokenReq);
    }

    @Override
    public CommonResp<Object> register(@RequestBody @Valid RegisterReq registerReq) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(registerReq.getPassword());
        ComUser comUser = ComUser.builder()
                .account(registerReq.getAccount())
                .password(password)
                .userName(registerReq.getUserName())
                .status((byte) 1)
                .createTime(new Date())
                .build();
        comUserService.save(comUser);
        return RespEntity.ok();
    }

    @Override
    public Boolean loginOut() {
        String token = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        return tokenServices.revokeToken(token);
    }

    @Override
    public OauthTokenResp refreshtoken(@ApiParam("需要拿refresh_token") @RequestParam("token") String token) {
        OauthTokenReq oauthTokenReq = OauthTokenReq.builder()
                .applicationName(loginProperties.getApplicationName())
                .client_id(loginProperties.getClientId())
                .client_secret(loginProperties.getClientSecret())
                .grant_type("refresh_token")
                .refresh_token(token)
                .build();
        return oauthEnpointFeign.login(oauthTokenReq);
    }

}
