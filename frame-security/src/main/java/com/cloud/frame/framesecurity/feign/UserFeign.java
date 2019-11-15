package com.cloud.frame.framesecurity.feign;

import com.cloud.frame.framesecurity.feign.annotation.AuthorizedUserFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Validated
@Api(tags = "1、用户表")
@AuthorizedUserFeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface UserFeign {

    @GetMapping(value = "/comuser/detail")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="userId", value="主键",required = true)
    Object getUserDetailById(@RequestParam("userId") @NotNull Long userId);

}
