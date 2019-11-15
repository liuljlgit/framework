package com.cloud.frame.frameauth.controller;

import com.cloud.frame.authclient.entity.ComUser;
import com.cloud.frame.authclient.feign.ComUserFeign;
import com.cloud.frame.frameauth.service.IComUserService;
import com.cloud.frame.framesecurity.feign.UserFeign;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "1、用户表")
public class ComUserCtrl implements ComUserFeign , UserFeign {

    @Autowired
    private IComUserService comUserService;

    @Override
    public CommonResp<ComUser> selectById(@RequestParam("userId") @NotNull Long userId) {
        return RespEntity.ok(comUserService.selectById(userId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComUser>> selectList(@RequestBody ComUser comUser){
        return RespEntity.ok(comUserService.selectList(comUser));
    }

    @Override
    public CommonResp<PageBean<ComUser>> selectPage(@RequestBody ComUser comUser) {
        return RespEntity.ok(comUserService.selectPage(comUser));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComUser comUser) {
        comUserService.save(comUser);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="userId") @NotNull Long userId) {
        comUserService.deleteById(userId);
        return RespEntity.ok();
    }

    @Override
    public Object getUserDetailById(@NotNull @RequestParam(value="userId") Long userId) {
        return comUserService.selectById(userId);
    }
}