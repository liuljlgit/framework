package com.cloud.frame.framesecurity.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * security拦截器
 *
 * @author jun
 */
@Slf4j
public class PermissionAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    private RedisTemplate<String,Object> redisTemplate;

    public PermissionAccessDecisionVoter(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //把权限->请求路径列表的数据放到redis中进行权限验证,每次从redis中获取数据进行验证

        return ACCESS_GRANTED;
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