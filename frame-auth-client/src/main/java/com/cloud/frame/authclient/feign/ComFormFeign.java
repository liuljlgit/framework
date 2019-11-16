package com.cloud.frame.authclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.authclient.entity.ComForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "8、表单权限管理表")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface ComFormFeign {

    @GetMapping(value = "/comform/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="formId", value="主键",required = true)
    CommonResp<ComForm> selectById(@RequestParam("formId") @NotNull Long formId);

    @PostMapping(value = "/comform/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<ComForm>> selectList(@RequestBody ComForm comForm);

    @PostMapping(value = "/comform/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<ComForm>> selectPage(@RequestBody ComForm comForm);

    @PostMapping(value = "/comform/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody ComForm comForm);

    @DeleteMapping(value = "/comform/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="formId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="formId") @NotNull Long formId);

}
