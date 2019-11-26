package com.cloud.frame.authclient.feign;

import com.cloud.frame.authclient.entity.ComFormRole;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Api(tags = "9. 角色表单权限关联表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComFormRoleFeign {

    @GetMapping(value = "/comformrole/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="rfId", value="主键",required = true)
    CommonResp<ComFormRole> selectById(@RequestParam("rfId") @NotNull Long rfId);

    @PostMapping(value = "/comformrole/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComFormRole>> selectList(@RequestBody ComFormRole comFormRole);

    @PostMapping(value = "/comformrole/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComFormRole>> selectPage(@RequestBody ComFormRole comFormRole);

    @PostMapping(value = "/comformrole/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComFormRole comFormRole);

    @DeleteMapping(value = "/comformrole/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="rfId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="rfId") @NotNull Long rfId);

}
