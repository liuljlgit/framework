package com.cloud.frame.framesecurity.voter;

import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.framesecurity.config.IgnoreUrl;
import com.cloud.frame.framesecurity.constant.SecurityConstants;
import com.cloud.frame.framesecurity.feign.SecurityFeign;
import com.cloud.frame.framesecurity.util.UrlUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 请求权限校验
 * @author liulijun
 */
@Slf4j
public class PermissionAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    private RedisTemplate<String,Object> redisTemplate;

    private SecurityFeign securityFeign;

    private String resourceId;

    private Set<String> urlSet;

    public PermissionAccessDecisionVoter(RedisTemplate<String, Object> redisTemplate, SecurityFeign securityFeign,
                                         String resourceId, IgnoreUrl ignoreUrl) {
        this.redisTemplate = redisTemplate;
        this.securityFeign = securityFeign;
        this.resourceId = resourceId;
        this.urlSet = new HashSet<>(ignoreUrl.getUri());
    }

    @Override
    @SuppressWarnings("unchecked")
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String servletPath = fi.getHttpRequest().getServletPath();
        //忽视鉴权开放
        Boolean isIgnoreUrl = UrlUtil.matching(urlSet, servletPath);
        if(isIgnoreUrl){
            return ACCESS_GRANTED;
        }

        //获取资源服务器前缀
        Map<Object, Object> resourceGatewayPrefixMap = redisTemplate.opsForHash().entries(SecurityConstants.RESOURCE_GATEWAY_PREFIX_MAP);
        if(resourceGatewayPrefixMap.isEmpty()){
            resourceGatewayPrefixMap = securityFeign.getResourceGatewayPrefixMap();
        }
        //servletPath组装
        servletPath = resourceGatewayPrefixMap.getOrDefault(resourceId,"") + servletPath;

        //获取角色对应的URL权限
        Map<Object, Object> rolekeyDetailsMap = redisTemplate.opsForHash().entries(SecurityConstants.ROLEKEY_DETAILS_MAP);
        if(rolekeyDetailsMap.isEmpty()){
            rolekeyDetailsMap = securityFeign.getRolekeyDetailsMap();
        }
        //URL权限判断
        Set<String> perUrlList = Sets.newHashSet();
        Set<String> forBidUrlList = Sets.newHashSet();
        for (GrantedAuthority authority : authorities) {
            String authStr = (String)rolekeyDetailsMap.getOrDefault(authority.getAuthority(), null);
            JSONObject authObj = JSONObject.parseObject(authStr);
            String permitUrls = authObj.getString("permitUrls");
            String forbidUrls = authObj.getString("forbidUrls");
            if(!StringUtils.isEmpty(permitUrls)){
                perUrlList.addAll(Arrays.stream(permitUrls.split(",")).collect(Collectors.toList()));
            }
            if(!StringUtils.isEmpty(forbidUrls)){
                forBidUrlList.addAll(Arrays.stream(forbidUrls.split(",")).collect(Collectors.toList()));
            }
        }
        Boolean preMatch = UrlUtil.matching(perUrlList, servletPath);
        Boolean forBitMatch = UrlUtil.matching(forBidUrlList, servletPath);
        if(forBitMatch || !preMatch){
            return ACCESS_DENIED;
        } else {
            return ACCESS_GRANTED;
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}