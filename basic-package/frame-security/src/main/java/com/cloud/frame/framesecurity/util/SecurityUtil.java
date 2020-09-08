package com.cloud.frame.framesecurity.util;

import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.framesecurity.constant.SecurityConstants;
import com.cloud.frame.framesecurity.entity.LoginUser;
import com.cloud.frame.framesecurity.feign.SecurityFeign;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * security工具类
 * @author Liulj
 * @version v 0.1 2019/11/14 18:02
 */
public class SecurityUtil {

    private static SecurityFeign securityFeign = ApplicationContextUtil.getBean(SecurityFeign.class);

    private static RedisTemplate<String,Object> redisTemplate = ApplicationContextUtil.getBean(RedisTemplate.class);

    /**
     * 获取当前登录用户
     * @return
     */
    public static LoginUser getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication auth2Authentication = (OAuth2Authentication)authentication;
            String principal = (String)auth2Authentication.getPrincipal();
            if(!StringUtils.isEmpty(principal)){
                Object loginUserObj = redisTemplate.opsForHash().get(SecurityConstants.PRINCIPAL_LOGINUSER_MAP, principal);
                if(Objects.isNull(loginUserObj)){
                    loginUserObj = securityFeign.getLoginUserByPrincipal(principal);
                }
                return JSONObject.parseObject(loginUserObj.toString(),LoginUser.class);
            }
        }
        return null;
    }
}
