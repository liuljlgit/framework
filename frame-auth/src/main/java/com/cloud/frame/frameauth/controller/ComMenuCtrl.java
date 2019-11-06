package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComMenuService;
import com.cloud.frame.authclient.entity.ComMenu;
import com.cloud.frame.authclient.feign.ComMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "3、菜单表")
public class ComMenuCtrl implements ComMenuFeign {

    @Autowired
    private IComMenuService comMenuService;

    @Override
    public CommonResp<ComMenu> selectById(@RequestParam("menuId") @NotNull Long menuId) {
        return RespEntity.ok(comMenuService.selectById(menuId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComMenu>> selectList(@RequestBody ComMenu comMenu){
        return RespEntity.ok(comMenuService.selectList(comMenu));
    }

    @Override
    public CommonResp<PageBean<ComMenu>> selectPage(@RequestBody ComMenu comMenu) {
        return RespEntity.ok(comMenuService.selectPage(comMenu));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComMenu comMenu) {
        comMenuService.save(comMenu);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="menuId") @NotNull Long menuId) {
        comMenuService.deleteById(menuId);
        return RespEntity.ok();
    }

}