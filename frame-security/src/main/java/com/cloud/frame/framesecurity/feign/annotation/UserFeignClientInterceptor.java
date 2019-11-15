package com.cloud.frame.framesecurity.feign.annotation;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Liulj
 * @version v 0.1 2019/11/15 15:33
 */
@Slf4j
public class UserFeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest httpServletRequest = getHttpServletRequest();
        if(Objects.nonNull(httpServletRequest)){
            String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
            if(StringUtils.isEmpty(tokenHeader)){
                return;
            }
            template.header(AUTHORIZATION_HEADER, tokenHeader);
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

}
