package com.cloud.frame.gateway.controller;

import com.alibaba.fastjson.JSONArray;
import com.cloud.frame.gateway.constant.ServiceConstant;
import com.cloud.frame.gateway.entity.GateWayRoute;
import com.cloud.frame.gateway.route.DbDynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RefreshGateWayController {

    @Autowired
    DbDynamicRouteService dbDynamicRouteService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/refresh/routes")
    public Mono refreshRoutes() {
        String gatewayRoutesStr = restTemplate.getForObject("http://" + ServiceConstant.FRAME_AUTH_SERVER + "/gatewayroutes",String.class);
        List<GateWayRoute> gateWayRoutes = JSONArray.parseArray(gatewayRoutesStr,GateWayRoute.class);
        dbDynamicRouteService.updateGatewayRoutes(gateWayRoutes);
        return Mono.empty();
    }

}
