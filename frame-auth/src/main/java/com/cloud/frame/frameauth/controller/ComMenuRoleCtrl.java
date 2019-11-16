package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComMenuRoleService;
import com.cloud.frame.authclient.entity.ComMenuRole;
import com.cloud.frame.authclient.feign.ComMenuRoleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "5、角色表")
public class ComMenuRoleCtrl implements ComMenuRoleFeign {

    @Autowired
    private IComMenuRoleService comMenuRoleService;

    @Override
    public CommonResp<ComMenuRole> selectById(@RequestParam("mrId") @NotNull Long mrId) {
        return RespEntity.ok(comMenuRoleService.selectById(mrId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComMenuRole>> selectList(@RequestBody ComMenuRole comMenuRole){
        return RespEntity.ok(comMenuRoleService.selectList(comMenuRole));
    }

    @Override
    public CommonResp<PageBean<ComMenuRole>> selectPage(@RequestBody ComMenuRole comMenuRole) {
        return RespEntity.ok(comMenuRoleService.selectPage(comMenuRole));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComMenuRole comMenuRole) {
        comMenuRoleService.save(comMenuRole);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="mrId") @NotNull Long mrId) {
        comMenuRoleService.deleteById(mrId);
        return RespEntity.ok();
    }

}