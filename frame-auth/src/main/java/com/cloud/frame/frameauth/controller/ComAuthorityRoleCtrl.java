package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComAuthorityRoleService;
import com.cloud.frame.authclient.entity.ComAuthorityRole;
import com.cloud.frame.authclient.feign.ComAuthorityRoleFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "7、权限角色关联表")
public class ComAuthorityRoleCtrl implements ComAuthorityRoleFeign {

    @Autowired
    private IComAuthorityRoleService comAuthorityRoleService;

    @Override
    public CommonResp<ComAuthorityRole> selectById(@RequestParam("arId") @NotNull Long arId) {
        return RespEntity.ok(comAuthorityRoleService.selectById(arId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComAuthorityRole>> selectList(@RequestBody ComAuthorityRole comAuthorityRole){
        return RespEntity.ok(comAuthorityRoleService.selectList(comAuthorityRole));
    }

    @Override
    public CommonResp<PageBean<ComAuthorityRole>> selectPage(@RequestBody ComAuthorityRole comAuthorityRole) {
        return RespEntity.ok(comAuthorityRoleService.selectPage(comAuthorityRole));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComAuthorityRole comAuthorityRole) {
        comAuthorityRoleService.save(comAuthorityRole);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="arId") @NotNull Long arId) {
        comAuthorityRoleService.deleteById(arId);
        return RespEntity.ok();
    }

}