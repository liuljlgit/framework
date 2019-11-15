package com.cloud.frame.framesecurity.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.frame.framesecurity.entity.LoginUser;
import com.cloud.frame.framesecurity.feign.UserFeign;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;
import java.util.Objects;

/**
 * security工具类
 * @author Liulj
 * @version v 0.1 2019/11/14 18:02
 */
public class SecurityUtil {

    private static UserFeign userFeign = ApplicationContextUtil.getBean(UserFeign.class);

    /**
     * 获取当前登录用户
     * @return
     */
    public static LoginUser getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication auth2Authentication = (OAuth2Authentication)authentication;
            Object details = auth2Authentication.getUserAuthentication().getDetails();
            if(details instanceof Map){
                Map<String,Object> detailMap = (Map)details;
                JSONObject user = JSONObject.parseObject(JSON.toJSONString(detailMap.get("principal")));
                Object userDetail = userFeign.getUserDetailById(user.getLong("id"));
                if(Objects.nonNull(userDetail)){
                    return JSONObject.parseObject(JSON.toJSONString(userDetail),LoginUser.class);
                }
            }
        }
        return null;
    }
}
