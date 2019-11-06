package com.cloud.frame.framesecurity.handler;

import com.alibaba.fastjson.JSONObject;
import com.cloud.ftl.ftlbasic.webEntity.CodeEnum;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足异常类
 * @author lijun
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("权限不足",accessDeniedException);
        response.setStatus(CodeEnum.EXEC_UNAUTHORIZED_DENY.getCode());
        response.getWriter().write(JSONObject.toJSONString(RespEntity.error(CodeEnum.EXEC_UNAUTHORIZED_DENY)));
    }

}
