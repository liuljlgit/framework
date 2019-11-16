package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComAuthorityService;
import com.cloud.frame.authclient.entity.ComAuthority;
import com.cloud.frame.authclient.feign.ComAuthorityFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "6、权限表")
public class ComAuthorityCtrl implements ComAuthorityFeign {

    @Autowired
    private IComAuthorityService comAuthorityService;

    @Override
    public CommonResp<ComAuthority> selectById(@RequestParam("authId") @NotNull Long authId) {
        return RespEntity.ok(comAuthorityService.selectById(authId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComAuthority>> selectList(@RequestBody ComAuthority comAuthority){
        return RespEntity.ok(comAuthorityService.selectList(comAuthority));
    }

    @Override
    public CommonResp<PageBean<ComAuthority>> selectPage(@RequestBody ComAuthority comAuthority) {
        return RespEntity.ok(comAuthorityService.selectPage(comAuthority));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComAuthority comAuthority) {
        comAuthorityService.save(comAuthority);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="authId") @NotNull Long authId) {
        comAuthorityService.deleteById(authId);
        return RespEntity.ok();
    }

}