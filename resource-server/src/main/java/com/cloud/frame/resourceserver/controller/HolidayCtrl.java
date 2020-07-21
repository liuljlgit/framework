package com.cloud.frame.resourceserver.controller;

import com.cloud.frame.framesecurity.entity.LoginUser;
import com.cloud.frame.framesecurity.util.SecurityUtil;
import com.cloud.frame.resourceclient.entity.Holiday;
import com.cloud.frame.resourceclient.feign.HolidayFeign;
import com.cloud.frame.resourceserver.service.IHolidayService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@Validated
@Api(tags = "调频-数据管理：1-4、节假日主表（数据来自汇电）")
public class HolidayCtrl implements HolidayFeign {

    @Autowired
    private IHolidayService holidayService;

    @Override
    public CommonResp<Holiday> selectById(@RequestParam("hId") @NotNull Long hId) {
        return RespEntity.ok(holidayService.selectById(hId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<Holiday>> selectList(@RequestBody Holiday holiday, HttpServletRequest request){
        LoginUser loginUser = SecurityUtil.getLoginUser();
        return RespEntity.ok(holidayService.selectList(holiday));
    }

    @Override
    public CommonResp<PageBean<Holiday>> selectPage(@RequestBody Holiday holiday) {
        return RespEntity.ok(holidayService.selectPage(holiday));
    }

    @Override
    public CommonResp<Object> save(@RequestBody Holiday holiday) {
        holidayService.save(holiday);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="hId") @NotNull Long hId) {
        holidayService.deleteById(hId);
        return RespEntity.ok();
    }

}