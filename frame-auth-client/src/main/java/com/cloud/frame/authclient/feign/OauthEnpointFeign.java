package com.cloud.frame.authclient.feign;

import com.cloud.frame.authclient.req.LoginReq;
import com.cloud.frame.authclient.resp.LoginResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(tags = "oauth2接口")
@FeignClient(value = "${frame.auth.server:frame-auth-server}")
public interface OauthEnpointFeign {

    /**
     * oauth2.0登录接口
     * @param loginReq 登录信息
     * @return
     */
    @ApiOperation(value = "登录" , notes = "author: llj")
    @RequestMapping(method = RequestMethod.POST,value = "/oauth/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LoginResp login(@RequestBody LoginReq loginReq);

}
