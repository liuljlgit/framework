package com.cloud.frame.gateway.runner;

import com.alibaba.fastjson.JSONArray;
import com.cloud.frame.gateway.constant.ServiceConstant;
import com.cloud.frame.gateway.entity.GateWayRoute;
import com.cloud.frame.gateway.route.DbDynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class InitGateWayRoutesRunner implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    DbDynamicRouteService dbDynamicRouteService;

    @Override
    public void run(String... args) throws Exception {
        log.info("~~ 初始化gatewayroutes开始 ~~");
        while (true){
            List<ServiceInstance> instances = discoveryClient.getInstances(ServiceConstant.FRAME_AUTH_SERVER);
            if(!CollectionUtils.isEmpty(instances)){
                try{
                    String gatewayRoutesStr = restTemplate.getForObject("http://" + ServiceConstant.FRAME_AUTH_SERVER + "/gatewayroutes",String.class);
                    List<GateWayRoute> gateWayRoutes = JSONArray.parseArray(gatewayRoutesStr,GateWayRoute.class);
                    dbDynamicRouteService.updateGatewayRoutes(gateWayRoutes);
                    log.info("~~ 刷新缓存成功，初始化gatewayroutes结束 ~~");
                    break;
                } catch (Exception e){
                    log.info("调用接口gatewayroutes失败，进行5秒休眠，然后重新调度");
                    Thread.sleep(5*1000);
                }
            } else {
                log.info("当前认证服务尚未启动，无法获取gatewayroutes，进行5秒休眠，然后重新调度");
                Thread.sleep(5*1000);
            }
        }
    }
}
