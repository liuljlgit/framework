package com.cloud.frame.resourceclient.feign;

import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.*;
import com.cloud.frame.resourceclient.entity.Holiday;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Api(tags = "调频-数据管理：1-4、节假日主表（数据来自汇电）")
@FeignClient(name = "${frame.resource.server:frame-resource-server}")
public interface HolidayFeign {

    @GetMapping(value = "/holiday/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="hId", value="主键",required = true)
    CommonResp<Holiday> selectById(@RequestParam("hId") @NotNull Long hId);

    @PostMapping(value = "/holiday/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<Holiday>> selectList(@RequestBody Holiday holiday);

    @PostMapping(value = "/holiday/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<Holiday>> selectPage(@RequestBody Holiday holiday);

    @PostMapping(value = "/holiday/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody Holiday holiday);

    @DeleteMapping(value = "/holiday/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="hId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="hId") @NotNull Long hId);

}
