package com.cloud.frame.frameauth.controller;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.frameauth.service.IComFormService;
import com.cloud.frame.authclient.entity.ComForm;
import com.cloud.frame.authclient.feign.ComFormFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "8、表单权限管理表")
public class ComFormCtrl implements ComFormFeign {

    @Autowired
    private IComFormService comFormService;

    @Override
    public CommonResp<ComForm> selectById(@RequestParam("formId") @NotNull Long formId) {
        return RespEntity.ok(comFormService.selectById(formId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComForm>> selectList(@RequestBody ComForm comForm){
        return RespEntity.ok(comFormService.selectList(comForm));
    }

    @Override
    public CommonResp<PageBean<ComForm>> selectPage(@RequestBody ComForm comForm) {
        return RespEntity.ok(comFormService.selectPage(comForm));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComForm comForm) {
        comFormService.save(comForm);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="formId") @NotNull Long formId) {
        comFormService.deleteById(formId);
        return RespEntity.ok();
    }

}