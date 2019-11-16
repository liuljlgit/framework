package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComMenuRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "5、角色表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComMenuRoleFeign {

    @GetMapping(value = "/commenurole/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="mrId", value="主键",required = true)
    CommonResp<ComMenuRole> selectById(@RequestParam("mrId") @NotNull Long mrId);

    @PostMapping(value = "/commenurole/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComMenuRole>> selectList(@RequestBody ComMenuRole comMenuRole);

    @PostMapping(value = "/commenurole/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComMenuRole>> selectPage(@RequestBody ComMenuRole comMenuRole);

    @PostMapping(value = "/commenurole/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComMenuRole comMenuRole);

    @DeleteMapping(value = "/commenurole/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="mrId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="mrId") @NotNull Long mrId);

}
