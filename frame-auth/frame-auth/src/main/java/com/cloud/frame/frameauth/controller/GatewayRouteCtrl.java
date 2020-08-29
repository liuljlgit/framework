package com.cloud.frame.frameauth.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.frame.authclient.entity.GatewayRoute;
import com.cloud.frame.authclient.feign.GatewayRouteFeign;
import com.cloud.frame.frameauth.service.IGatewayRouteService;
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
@Api(tags = "网关路由规则配置")
public class GatewayRouteCtrl implements GatewayRouteFeign {

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @Override
    public CommonResp<GatewayRoute> selectById(@RequestParam("grId") @NotNull Long grId) {
        return RespEntity.ok(gatewayRouteService.selectById(grId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<GatewayRoute>> selectList(@RequestBody GatewayRoute gatewayRoute){
        return RespEntity.ok(gatewayRouteService.selectList(gatewayRoute));
    }

    @Override
    public CommonResp<PageBean<GatewayRoute>> selectPage(@RequestBody GatewayRoute gatewayRoute) {
        return RespEntity.ok(gatewayRouteService.selectPage(gatewayRoute));
    }

    @Override
    public CommonResp<Object> save(@RequestBody GatewayRoute gatewayRoute) {
        gatewayRouteService.save(gatewayRoute);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="grId") @NotNull Long grId) {
        gatewayRouteService.deleteById(grId);
        return RespEntity.ok();
    }

    @Override
    public String refreshGateways() {
        List<GatewayRoute> gatewayRoutes = gatewayRouteService.selectList(new GatewayRoute());
        return JSON.toJSONString(gatewayRoutes);
    }

}