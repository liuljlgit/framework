package com.cloud.frame.frameauth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cloud.frame.authclient.dto.MenuTreeDto;
import com.cloud.frame.authclient.util.TreeBuilder;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComMenuService;
import com.cloud.frame.authclient.entity.ComMenu;
import com.cloud.frame.authclient.feign.ComMenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public CommonResp<List<MenuTreeDto>> selectList(@RequestBody ComMenu comMenu){
        List<TreeBuilder.Node> menuTreeDtos = comMenuService.selectList(comMenu).stream().map(e -> {
            MenuTreeDto menuTreeDto = new MenuTreeDto();
            BeanUtils.copyProperties(e, menuTreeDto);
            return menuTreeDto;
        }).collect(Collectors.toList());
        List<TreeBuilder.Node> nodes = TreeBuilder.buildTree(menuTreeDtos, "0");
        List<MenuTreeDto> trees = JSONArray.parseArray(JSON.toJSONString(nodes), MenuTreeDto.class);
        return RespEntity.ok(trees);
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