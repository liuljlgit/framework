package com.cloud.frame.framesecurity.handler;

import com.cloud.frame.framesecurity.entity.SecurityAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * security拦截器
 *
 * @author jun
 */
@Slf4j
public class PermissionAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    public PermissionAccessDecisionVoter() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public int vote(Authentication authentication, FilterInvocation fi,
                    Collection<ConfigAttribute> attributes) {
        //获取权限列表
        Set<SecurityAuthority> authorities = (HashSet<SecurityAuthority>) authentication.getAuthorities();

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