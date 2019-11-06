package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComUserRoleService;
import com.cloud.frame.authclient.entity.ComUserRole;
import com.cloud.frame.authclient.feign.ComUserRoleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "4、用户角色关联表")
public class ComUserRoleCtrl implements ComUserRoleFeign {

    @Autowired
    private IComUserRoleService comUserRoleService;

    @Override
    public CommonResp<ComUserRole> selectById(@RequestParam("urId") @NotNull Long urId) {
        return RespEntity.ok(comUserRoleService.selectById(urId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComUserRole>> selectList(@RequestBody ComUserRole comUserRole){
        return RespEntity.ok(comUserRoleService.selectList(comUserRole));
    }

    @Override
    public CommonResp<PageBean<ComUserRole>> selectPage(@RequestBody ComUserRole comUserRole) {
        return RespEntity.ok(comUserRoleService.selectPage(comUserRole));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComUserRole comUserRole) {
        comUserRoleService.save(comUserRole);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="urId") @NotNull Long urId) {
        comUserRoleService.deleteById(urId);
        return RespEntity.ok();
    }

}