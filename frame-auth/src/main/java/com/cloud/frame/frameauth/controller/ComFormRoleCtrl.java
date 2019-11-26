package com.cloud.frame.frameauth.controller;

import com.cloud.frame.authclient.entity.ComFormRole;
import com.cloud.frame.authclient.feign.ComFormRoleFeign;
import com.cloud.frame.frameauth.service.IComFormRoleService;
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
@Api(tags = "9. 角色表单权限关联表")
public class ComFormRoleCtrl implements ComFormRoleFeign {

    @Autowired
    private IComFormRoleService comFormRoleService;

    @Override
    public CommonResp<ComFormRole> selectById(@RequestParam("rfId") @NotNull Long rfId) {
        return RespEntity.ok(comFormRoleService.selectById(rfId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComFormRole>> selectList(@RequestBody ComFormRole comFormRole){
        return RespEntity.ok(comFormRoleService.selectList(comFormRole));
    }

    @Override
    public CommonResp<PageBean<ComFormRole>> selectPage(@RequestBody ComFormRole comFormRole) {
        return RespEntity.ok(comFormRoleService.selectPage(comFormRole));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComFormRole comFormRole) {
        comFormRoleService.save(comFormRole);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="rfId") @NotNull Long rfId) {
        comFormRoleService.deleteById(rfId);
        return RespEntity.ok();
    }

}