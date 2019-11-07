package com.cloud.frame.authclient.feign;

import com.cloud.frame.authclient.req.ClientDetailsReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "自定义ClientDetails接口")
@FeignClient(value = "${frame.auth.server:frame-auth-server}")
public interface ClientDetailsFeign {

    @PostMapping("/clientdetails/add")
    @ApiOperation(value = "增加客户端信息" , notes = "author: llj")
    void saveClientDetails(@RequestBody ClientDetailsReq clientDetailsReq);

    @GetMapping("/clientdetails/delete")
    @ApiOperation(value = "增加客户端信息" , notes = "author: llj")
    void deleteClientDetails(@RequestParam("clientId") String clientId);
}
