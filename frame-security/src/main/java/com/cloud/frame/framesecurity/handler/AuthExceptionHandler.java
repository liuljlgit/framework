package com.cloud.frame.framesecurity.handler;

import com.alibaba.fastjson.JSONObject;
import com.cloud.ftl.ftlbasic.webEntity.CodeEnum;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * token解析失败异常处理
 * @author lijun
 */
@Slf4j
public class AuthExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.error("401认证失败",authException);
        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = authException.getCause();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            response.setStatus(CodeEnum.EXEC_UNAUTHORIZED_401.getCode());
            response.getWriter().write(JSONObject.toJSONString(RespEntity.error(CodeEnum.EXEC_UNAUTHORIZED_401)));
        } catch (IOException e) {
            log.error("401认证失败",e);
        }
    }

}