package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComAuthorityRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "7、权限角色关联表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComAuthorityRoleFeign {

    @GetMapping(value = "/comauthorityrole/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="arId", value="主键",required = true)
    CommonResp<ComAuthorityRole> selectById(@RequestParam("arId") @NotNull Long arId);

    @PostMapping(value = "/comauthorityrole/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComAuthorityRole>> selectList(@RequestBody ComAuthorityRole comAuthorityRole);

    @PostMapping(value = "/comauthorityrole/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComAuthorityRole>> selectPage(@RequestBody ComAuthorityRole comAuthorityRole);

    @PostMapping(value = "/comauthorityrole/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComAuthorityRole comAuthorityRole);

    @DeleteMapping(value = "/comauthorityrole/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="arId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="arId") @NotNull Long arId);

}
