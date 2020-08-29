package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "2、角色表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComRoleFeign {

    @GetMapping(value = "/comrole/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="roleId", value="主键",required = true)
    CommonResp<ComRole> selectById(@RequestParam("roleId") @NotNull Long roleId);

    @PostMapping(value = "/comrole/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComRole>> selectList(@RequestBody ComRole comRole);

    @PostMapping(value = "/comrole/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComRole>> selectPage(@RequestBody ComRole comRole);

    @PostMapping(value = "/comrole/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComRole comRole);

    @DeleteMapping(value = "/comrole/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="roleId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="roleId") @NotNull Long roleId);

}
