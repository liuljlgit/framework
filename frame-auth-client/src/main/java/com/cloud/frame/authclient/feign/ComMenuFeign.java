package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComMenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "3、菜单表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComMenuFeign {

    @GetMapping(value = "/commenu/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="menuId", value="主键",required = true)
    CommonResp<ComMenu> selectById(@RequestParam("menuId") @NotNull Long menuId);

    @PostMapping(value = "/commenu/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComMenu>> selectList(@RequestBody ComMenu comMenu);

    @PostMapping(value = "/commenu/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComMenu>> selectPage(@RequestBody ComMenu comMenu);

    @PostMapping(value = "/commenu/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComMenu comMenu);

    @DeleteMapping(value = "/commenu/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="menuId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="menuId") @NotNull Long menuId);

    @GetMapping(value = "/commenu/tree")
    @ApiOperation(value = "得到菜单树", notes = "author: llj")
    CommonResp<Object> menuTree();

}
