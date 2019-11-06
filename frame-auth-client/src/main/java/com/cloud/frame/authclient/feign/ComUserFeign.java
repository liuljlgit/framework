package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "1、用户表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComUserFeign {

    @GetMapping(value = "/comuser/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="userId", value="主键",required = true)
    CommonResp<ComUser> selectById(@RequestParam("userId") @NotNull Long userId);

    @PostMapping(value = "/comuser/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComUser>> selectList(@RequestBody ComUser comUser);

    @PostMapping(value = "/comuser/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComUser>> selectPage(@RequestBody ComUser comUser);

    @PostMapping(value = "/comuser/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComUser comUser);

    @DeleteMapping(value = "/comuser/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="userId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="userId") @NotNull Long userId);

}
