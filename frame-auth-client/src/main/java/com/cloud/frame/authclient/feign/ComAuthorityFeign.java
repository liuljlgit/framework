package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComAuthority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "6、权限表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComAuthorityFeign {

    @GetMapping(value = "/comauthority/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="authId", value="主键",required = true)
    CommonResp<ComAuthority> selectById(@RequestParam("authId") @NotNull Long authId);

    @PostMapping(value = "/comauthority/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComAuthority>> selectList(@RequestBody ComAuthority comAuthority);

    @PostMapping(value = "/comauthority/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComAuthority>> selectPage(@RequestBody ComAuthority comAuthority);

    @PostMapping(value = "/comauthority/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComAuthority comAuthority);

    @DeleteMapping(value = "/comauthority/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="authId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="authId") @NotNull Long authId);

}
