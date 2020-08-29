package com.cloud.frame.framesecurity.feign.annotation;

import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

public class EnableGlobalAuthorizedFeignClientImpl {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String TRACE_ID = "traceId";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Bean
    public RequestInterceptor httpFeignInterceptor() {
        return template -> {
            HttpServletRequest httpServletRequest = getHttpServletRequest();
            if(Objects.nonNull(httpServletRequest)){
                String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
                if(StringUtils.isEmpty(tokenHeader)){
                    return;
                }
                template.header(AUTHORIZATION_HEADER, tokenHeader);
                template.header(TRACE_ID,httpServletRequest.getHeader(TRACE_ID));
            }
        };
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        } catch (Exception e) {
            return null;
        }
    }
}
