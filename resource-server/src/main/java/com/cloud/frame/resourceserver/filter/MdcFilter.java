package com.cloud.frame.resourceserver.filter;

import com.cloud.frame.framesecurity.filter.HeaderMapRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Order(-10)
@Component
@WebFilter(filterName = "mdcFilter" ,urlPatterns = "/**")
public class MdcFilter implements Filter  {

    private final static String TRACE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HeaderMapRequestWrapper headerMapRequestWrapper = new HeaderMapRequestWrapper((HttpServletRequest) servletRequest);
        String traceId = headerMapRequestWrapper.getHeader("traceId");
        if(StringUtils.isEmpty(traceId)){
            traceId = UUID.randomUUID().toString().replace("-","");
        }
        MDC.put(TRACE_ID,traceId);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
