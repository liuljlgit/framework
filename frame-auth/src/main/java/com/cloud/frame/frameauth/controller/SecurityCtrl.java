package com.cloud.frame.frameauth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.authclient.entity.ComRole;
import com.cloud.frame.authclient.entity.ComUser;
import com.cloud.frame.authclient.entity.GatewayRoute;
import com.cloud.frame.frameauth.service.IComRoleService;
import com.cloud.frame.frameauth.service.IComUserService;
import com.cloud.frame.frameauth.service.IGatewayRouteService;
import com.cloud.frame.framesecurity.constant.RedisKey;
import com.cloud.frame.framesecurity.entity.LoginUser;
import com.cloud.frame.framesecurity.feign.SecurityFeign;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:44
 */
@Slf4j
@Validated
@RestController
@Api(tags = "Security相关接口")
public class SecurityCtrl implements SecurityFeign {

    @Autowired
    private IComUserService comUserService;

    @Autowired
    private IComRoleService comRoleService;

    @Autowired
    private IGatewayRouteService gatewayRouteService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Map<Object, Object> getResourceGatewayPrefixMap() {
        Map<Object, Object> resourceGatewayPrefixMap = gatewayRouteService.selectList(new GatewayRoute()).stream()
                .filter(e-> !StringUtils.isEmpty(e.getReourceId()))
                .collect(Collectors.toMap(e->e.getReourceId(), e->e.getRegexpUrl()));
        redisTemplate.opsForHash().putAll(RedisKey.RESOURCE_GATEWAY_PREFIX_MAP,resourceGatewayPrefixMap);
        return resourceGatewayPrefixMap;
    }

    @Override
    public Map<Object, Object> getRolekeyDetailsMap() {
        Map<Object, Object> rolekeyDetailsMap = comRoleService.selectList(new ComRole()).stream()
                .collect(Collectors.toMap(e -> "ROLE_" + e.getRoleCode(), e-> JSONObject.toJSONString(e)));
        redisTemplate.opsForHash().putAll(RedisKey.ROLEKEY_DETAILS_MAP,rolekeyDetailsMap);
        return rolekeyDetailsMap;
    }

    @Override
    public Object getLoginUserByPrincipal(@NotNull String principal) {
        ComUser comUser = ComUser.builder()
                .userName(principal)
                .build();
        ComUser dbUser = comUserService.selectOne(comUser);
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(dbUser,loginUser);
        if(Objects.nonNull(dbUser)){
            redisTemplate.opsForHash().put(RedisKey.PRINCIPAL_LOGINUSER_MAP,dbUser.getUserName(), JSON.toJSONString(loginUser));
        }
        return dbUser;
    }

}
