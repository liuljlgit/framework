package com.cloud.frame.gateway.route;

import com.cloud.frame.authclient.entity.GatewayRoute;
import com.cloud.frame.authclient.feign.GatewayRouteFeign;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网关映射关系配置
 * @author Liulj
 * @version v 0.1 2019/11/12 18:14
 */
@Slf4j
@Service
public class GateWayRouteHandler implements RouteDefinitionRepository {

    @Autowired
    private GatewayRouteFeign gatewayRouteFeign;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static volatile boolean isReady = true;

    private volatile List<RouteDefinition> definitions = Lists.newArrayList();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        updateGateWayRoute();
        log.info("更新网关映射：{}",definitions);
        return Flux.fromIterable(definitions);
    }

    /**
     * 异步刷新缓存中网关映射关系
     */
    private void updateGateWayRoute() {
        if(isReady && gatewayRouteFeign != null) {
            threadPoolTaskExecutor.execute(()-> {
                try {
                    isReady = false;
                    GatewayRoute gatewayRoute = GatewayRoute.builder()
                            .status(BigDecimal.ONE)
                            .build();
                    List<GatewayRoute> gatewayRoutes = gatewayRouteFeign.selectList(gatewayRoute).getBody();

                    definitions = Lists.newArrayList();
                    if (!CollectionUtils.isEmpty(gatewayRoutes)) {
                        gatewayRoutes.forEach(r -> definitions.addAll(routeDefinitions(r.getRouteId(), r.getInstanceId(),
                                r.getRegexpUrl(), r.getPredicates().split(","))));
                    }
                    log.info("缓存中网关数据刷新：{}",definitions);
                } catch (Exception e) {
                    log.error("获取网关数据失败", e);
                }
                isReady = true;
            });
        }
    }


    private List<RouteDefinition> routeDefinitions(String id,String uri,String regexp,String... pattern){
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        int index = -1;
        if(pattern != null && pattern.length > 0){
            for(String p:pattern){
                RouteDefinition routeDefinition = new RouteDefinition();
                id = ++index == 0 ? id : id + "_" + index;
                routeDefinition.setId(id);
                routeDefinition.setUri(UriComponentsBuilder.fromUriString(uri).build().toUri());
                //断言规则
                PredicateDefinition predicateDefinition = new PredicateDefinition();
                predicateDefinition.setName("Path");
                predicateDefinition.setArgs(Map.of("pattern",p));
                routeDefinition.setPredicates(List.of(predicateDefinition));

                //过滤规则
                FilterDefinition filterDefinition = new FilterDefinition();
                filterDefinition.setName("RewritePath");
                filterDefinition.setArgs(Map.of("regexp",regexp + "/(?<remaining>.*)","replacement","/${remaining}"));
                routeDefinition.setFilters(List.of(filterDefinition));

                routeDefinitions.add(routeDefinition);
            }
        }
        return routeDefinitions;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

}
