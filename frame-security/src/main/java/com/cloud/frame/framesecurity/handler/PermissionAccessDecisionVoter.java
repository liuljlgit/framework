package com.cloud.frame.framesecurity.handler;

import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.framesecurity.constant.RoleConst;
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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * security拦截器
 *
 * @author Lulijun
 */
@Slf4j
public class PermissionAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    private RedisTemplate<String,Object> redisTemplate;

    private SecurityFeign securityFeign;

    private String serverName;

    public PermissionAccessDecisionVoter(RedisTemplate<String, Object> redisTemplate, SecurityFeign securityFeign, String serverName) {
        this.redisTemplate = redisTemplate;
        this.securityFeign = securityFeign;
        this.serverName = serverName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //把权限->请求路径列表的数据放到redis中进行权限验证,每次从redis中获取数据进行验证
        String servletPath = fi.getHttpRequest().getServletPath();
        //获取服务名->前缀信息
        Map<Object, Object> routeMap = redisTemplate.opsForHash().entries(RoleConst.ROUTE_MAP_KEY);
        if(routeMap.size() == 0 && !redisTemplate.hasKey(RoleConst.ROUTE_MAP_KEY)){
            routeMap = securityFeign.loadRouteSuffixInfo();
            redisTemplate.opsForHash().putAll(RoleConst.ROUTE_MAP_KEY,routeMap);
        }
        servletPath = routeMap.getOrDefault(serverName,"") + servletPath;

        //获取角色->权限信息
        Map<Object, Object> roleMap = redisTemplate.opsForHash().entries(RoleConst.ROLE_MAP_KEY);
        if(roleMap.size() == 0 && !redisTemplate.hasKey(RoleConst.ROLE_MAP_KEY)){
            roleMap = securityFeign.loadRoleAuthoritys();
            redisTemplate.opsForHash().putAll(RoleConst.ROLE_MAP_KEY,roleMap);
        }
        Set<String> perUrlList = Sets.newHashSet();
        Set<String> forBidUrlList = Sets.newHashSet();
        for (GrantedAuthority authority : authorities) {
            String authStr = (String)roleMap.getOrDefault(authority.getAuthority(), null);
            JSONObject authObj = JSONObject.parseObject(authStr);
            perUrlList.addAll(authObj.getJSONArray("perUrlList").toJavaList(String.class));
            forBidUrlList.addAll(authObj.getJSONArray("forBidUrlList").toJavaList(String.class));
        }
        Boolean preMatch = UrlUtil.matching(perUrlList, servletPath);
        Boolean forBitMatch = UrlUtil.matching(forBidUrlList, servletPath);
        if(forBitMatch || !preMatch){
            return ACCESS_DENIED;
        } else {
            return ACCESS_GRANTED;
        }
        //关于表单权限（前端给表单主键增加唯一性的标识）,后端记录这些标识进行不同权限返回不同的标识列表，拥有这些标识的才展示。
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