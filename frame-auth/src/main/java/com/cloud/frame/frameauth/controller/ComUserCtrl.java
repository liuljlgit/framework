package com.cloud.frame.frameauth.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.authclient.entity.*;
import com.cloud.frame.authclient.feign.ComUserFeign;
import com.cloud.frame.frameauth.service.*;
import com.cloud.frame.framesecurity.feign.SecurityFeign;
import com.cloud.ftl.ftlbasic.webEntity.CommonResp;
import com.cloud.ftl.ftlbasic.webEntity.PageBean;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
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
    private IComAuthorityRoleService comAuthorityRoleService;

    @Autowired
    private IComAuthorityService comAuthorityService;

    @Autowired
    private IGatewayRouteService gatewayRouteService;

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
    public Map<Object, Object> loadRoleAuthoritys() {
        Map<Object, Object> roleAuthoritysMap = Maps.newHashMap();
        //获取角色信息
        ComRole comRole = ComRole.builder()
                .isEnable((byte) 1)
                .build();
        Map<Long, String> roleMap = comRoleService.selectList(comRole).stream()
                .collect(Collectors.toMap(ComRole::getRoleId, ComRole::getRoleName));
        //获取角色对应的权限id列表
        Map<String, List<Long>> roleNameAuthoritysMap = comAuthorityRoleService.selectList(new ComAuthorityRole()).stream()
                .filter(e-> Objects.nonNull(roleMap.getOrDefault(e.getRoleId(),null)))
                .collect(Collectors.groupingBy(e -> roleMap.get(e.getRoleId()),
                        Collectors.mapping(ComAuthorityRole::getAuthId, Collectors.toList())));
        //获取权限信息
        ComAuthority comAuthority = ComAuthority.builder()
                .isEnable((byte)1)
                .build();
        Map<Long, ComAuthority> authorityMap = comAuthorityService.selectList(comAuthority).stream()
                .collect(Collectors.toMap(ComAuthority::getAuthId, Function.identity()));
        //获取角色->权限信息
        for (Map.Entry<String, List<Long>> entry : roleNameAuthoritysMap.entrySet()) {
            String roleName = entry.getKey();
            List<Long> authIds = entry.getValue();
            JSONObject authObj = new JSONObject();
            List<String> perUrlList = Lists.newArrayList();
            List<String> forBidUrlList = Lists.newArrayList();
            for (Long authId : authIds) {
                ComAuthority authority = authorityMap.getOrDefault(authId, null);
                if(StringUtils.isNotEmpty(authority.getPermitUrls())){
                    List<String> urls = Splitter.on(",")
                            .trimResults()
                            .splitToList(authority.getPermitUrls());
                    perUrlList.addAll(urls);
                }
                if(StringUtils.isNotEmpty(authority.getForbidUrls())){
                    List<String> urls = Splitter.on(",")
                            .trimResults()
                            .splitToList(authority.getForbidUrls());
                    forBidUrlList.addAll(urls);
                }
            }
            authObj.put("perUrlList",perUrlList);
            authObj.put("forBidUrlList",forBidUrlList);
            roleAuthoritysMap.put(roleName,authObj.toJSONString());
        }
        return roleAuthoritysMap;
    }

    @Override
    public Map<Object, Object> loadRouteSuffixInfo() {
        GatewayRoute gatewayRoute = GatewayRoute.builder().status(BigDecimal.ONE).build();
        return gatewayRouteService.selectList(gatewayRoute).stream()
                .collect(Collectors.toMap(GatewayRoute::getRouteId,GatewayRoute::getRegexpUrl));
    }
}