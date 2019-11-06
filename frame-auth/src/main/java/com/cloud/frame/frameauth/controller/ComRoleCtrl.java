package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComRoleService;
import com.cloud.frame.authclient.entity.ComRole;
import com.cloud.frame.authclient.feign.ComRoleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "2、角色表")
public class ComRoleCtrl implements ComRoleFeign {

    @Autowired
    private IComRoleService comRoleService;

    @Override
    public CommonResp<ComRole> selectById(@RequestParam("roleId") @NotNull Long roleId) {
        return RespEntity.ok(comRoleService.selectById(roleId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComRole>> selectList(@RequestBody ComRole comRole){
        return RespEntity.ok(comRoleService.selectList(comRole));
    }

    @Override
    public CommonResp<PageBean<ComRole>> selectPage(@RequestBody ComRole comRole) {
        return RespEntity.ok(comRoleService.selectPage(comRole));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComRole comRole) {
        comRoleService.save(comRole);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="roleId") @NotNull Long roleId) {
        comRoleService.deleteById(roleId);
        return RespEntity.ok();
    }

}