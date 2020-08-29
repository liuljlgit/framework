package com.cloud.frame.authclient.feign;

import com.cloud.frame.authclient.entity.GatewayRoute;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Api(tags = "网关路由规则配置")
@FeignClient(name = "${frame.auth.server:frame-auth-server}")
public interface GatewayRouteFeign {

    @GetMapping(value = "/gatewayroute/obj")
    @ApiOperation(value = "根据主键查询" , notes = "author: llj")
    @ApiImplicitParam(name="grId", value="主键",required = true)
    CommonResp<GatewayRoute> selectById(@RequestParam("grId") @NotNull Long grId);

    @PostMapping(value = "/gatewayroute/list")
    @ApiOperation(value = "查询所有列表" , notes = "author: llj")
    CommonResp<List<GatewayRoute>> selectList(@RequestBody GatewayRoute gatewayRoute);

    @PostMapping(value = "/gatewayroute/page")
    @ApiOperation(value = "分页查询" , notes = "author: llj")
    CommonResp<PageBean<GatewayRoute>> selectPage(@RequestBody GatewayRoute gatewayRoute);

    @PostMapping(value = "/gatewayroute/obj")
    @ApiOperation(value = "更新或者新增", notes = "author: llj")
    CommonResp<Object> save(@RequestBody GatewayRoute gatewayRoute);

    @DeleteMapping(value = "/gatewayroute/obj")
    @ApiOperation(value = "根据主键删除",notes = "author: llj")
    @ApiImplicitParam(name="grId", value="主键",required = true)
    CommonResp<Object> deleteById(@RequestParam(value="grId") @NotNull Long grId);

    @GetMapping(value = "/gatewayroutes")
    @ApiOperation(value = "刷新路由信息" , notes = "author: llj")
    String refreshGateways();

}
