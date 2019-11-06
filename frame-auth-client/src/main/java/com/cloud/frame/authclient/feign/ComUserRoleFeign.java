package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComUserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "4、用户角色关联表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComUserRoleFeign {

    @GetMapping(value = "/comuserrole/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="urId", value="主键",required = true)
    CommonResp<ComUserRole> selectById(@RequestParam("urId") @NotNull Long urId);

    @PostMapping(value = "/comuserrole/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComUserRole>> selectList(@RequestBody ComUserRole comUserRole);

    @PostMapping(value = "/comuserrole/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComUserRole>> selectPage(@RequestBody ComUserRole comUserRole);

    @PostMapping(value = "/comuserrole/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComUserRole comUserRole);

    @DeleteMapping(value = "/comuserrole/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="urId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="urId") @NotNull Long urId);

}
