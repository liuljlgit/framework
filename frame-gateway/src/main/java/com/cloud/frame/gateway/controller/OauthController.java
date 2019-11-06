package com.cloud.frame.gateway.controller;

import com.cloud.frame.authclient.entity.ComUser;
import com.cloud.frame.authclient.feign.ComUserFeign;
import com.cloud.frame.authclient.feign.OauthEnpointFeign;
import com.cloud.frame.authclient.feign.OauthFeign;
import com.cloud.frame.authclient.req.LoginReq;
import com.cloud.frame.authclient.req.RegisterReq;
import com.cloud.frame.authclient.req.UserReq;
import com.cloud.frame.authclient.resp.LoginResp;
import com.cloud.frame.authclient.resp.UserInfoResp;
import com.cloud.frame.framesecurity.config.LoginProperties;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 15:05
 */
@RestController
@RequestMapping("/oauth")
@Api(tags = "登录注册相关接口")
public class OauthController {

    @Autowired
    OauthFeign oauthFeign;

    @Autowired
    OauthEnpointFeign oauthEnpointFeign;

    @Autowired
    private LoginProperties loginProperties;

    @Autowired
    private ComUserFeign comUserFeign;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public LoginResp login(@RequestBody UserReq userReq){
        LoginReq loginReq = LoginReq.builder()
                .applicationName(loginProperties.getApplicationName())
                .client_id(loginProperties.getClientId())
                .client_secret(loginProperties.getClientSecret())
                .grant_type(loginProperties.getGrantType())
                .password(userReq.getPassword())
                .username(userReq.getUsername())
                .scope(loginProperties.getScope())
                .build();
        return oauthEnpointFeign.login(loginReq);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public CommonResp<Object> register(@RequestBody @Valid RegisterReq registerReq){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(registerReq.getPassword());
        return comUserFeign.save(ComUser.builder()
                .account(registerReq.getAccount())
                .password(password)
                .userName(registerReq.getUserName())
                .status((byte) 1)
                .createTime(new Date())
                .build());
    }

    @GetMapping("/refreshtoken")
    @ApiOperation(value = "刷新token")
    public LoginResp refreshtoken(@ApiParam("需要拿refresh_token") @RequestParam("token") String token){
        LoginReq loginReq = LoginReq.builder()
                .applicationName(loginProperties.getApplicationName())
                .client_id(loginProperties.getClientId())
                .client_secret(loginProperties.getClientSecret())
                .grant_type("refresh_token")
                .refresh_token(token)
                .build();
        return oauthEnpointFeign.login(loginReq);
    }

    @GetMapping("/loginout")
    @ApiOperation(value = "退出登录")
    public Boolean loginout(){
        String token = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        return oauthFeign.loginOut(token);
    }

    @GetMapping("/userinfo")
    @ApiOperation(value = "5、根据用户的token返回用户信息，菜单，权限等信息")
    public UserInfoResp getLoginUserInfo(){
        return null;
    }

}
