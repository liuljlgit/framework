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
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.error("鉴权失败",authException);
        Map<String, Object> map = new HashMap<String, Object>();
        Throwable cause = authException.getCause();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if(cause instanceof InvalidTokenException) {
                response.setStatus(CodeEnum.EXEC_UNAUTHORIZED.getCode());
                response.getWriter().write(JSONObject.toJSONString(RespEntity.error(CodeEnum.EXEC_UNAUTHORIZED)));
            }else{
                response.setStatus(CodeEnum.EXEC_FORBIDDEN.getCode());
                response.getWriter().write(JSONObject.toJSONString(RespEntity.error(CodeEnum.EXEC_FORBIDDEN)));
            }
        } catch (IOException e) {
            log.error("HttpServletResponse异常",e);
        }
    }

}