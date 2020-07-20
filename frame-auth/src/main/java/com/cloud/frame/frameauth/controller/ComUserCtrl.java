package com.cloud.frame.frameauth.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.authclient.entity.ComRole;
import com.cloud.frame.authclient.entity.ComUser;
import com.cloud.frame.authclient.entity.GatewayRoute;
import com.cloud.frame.authclient.feign.ComUserFeign;
import com.cloud.frame.frameauth.service.IComRoleService;
import com.cloud.frame.frameauth.service.IComUserService;
import com.cloud.frame.frameauth.service.IGatewayRouteService;
import com.cloud.frame.framesecurity.constant.RedisKey;
import com.cloud.frame.framesecurity.feign.SecurityFeign;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Validated
@Api(tags = "1、用户表")
public class ComUserCtrl implements ComUserFeign , SecurityFeign {

    @Autowired
    private IComUserService comUserService;

    @Autowired
    private IComRoleService comRoleService;

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public CommonResp<ComUser> selectById(@RequestParam("userId") @NotNull Long userId) {
        return RespEntity.ok(comUserService.selectById(userId,"没有符合条件的记录！"));
    }

    @Override
    public CommonResp<List<ComUser>> selectList(@RequestBody ComUser comUser){
        return RespEntity.ok(comUserService.selectList(comUser));
    }

    @Override
    public CommonResp<PageBean<ComUser>> selectPage(@RequestBody ComUser comUser) {
        return RespEntity.ok(comUserService.selectPage(comUser));
    }

    @Override
    public CommonResp<Object> save(@RequestBody ComUser comUser) {
        comUserService.save(comUser);
        return RespEntity.ok();
    }

    @Override
    public CommonResp<Object> deleteById(@RequestParam(value="userId") @NotNull Long userId) {
        comUserService.deleteById(userId);
        return RespEntity.ok();
    }

    @Override
    public Object getUserDetailById(@NotNull @RequestParam(value="userId") Long userId) {
        return comUserService.selectById(userId);
    }

    @Override
    public Map<Object, Object> getResourceGatewayPrefixMap() {
        Map<Object, Object> resourceGatewayPrefixMap = gatewayRouteService.selectList(new GatewayRoute()).stream()
                .filter(e-> !StringUtils.isEmpty(e.getReourceId()))
                .collect(Collectors.toMap(e->e.getReourceId(),e->e.getRegexpUrl()));
        redisTemplate.opsForHash().putAll(RedisKey.RESOURCE_GATEWAY_PREFIX_MAP,resourceGatewayPrefixMap);
        return resourceGatewayPrefixMap;
    }

    @Override
    public Map<Object, Object> getRolekeyDetailsMap() {
        Map<Object, Object> rolekeyDetailsMap = comRoleService.selectList(new ComRole()).stream()
                .collect(Collectors.toMap(e -> "ROLE_" + e.getRoleCode(), e->JSONObject.toJSONString(e)));
        redisTemplate.opsForHash().putAll(RedisKey.ROLEKEY_DETAILS_MAP,rolekeyDetailsMap);
        return rolekeyDetailsMap;
    }
}