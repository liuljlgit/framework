package com.cloud.frame.gateway.filter;

import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MdcConfig {

    private final static String TRACE_ID = "traceId";

    @Bean
    public GlobalFilter mdcFilter() {
        return (exchange, chain) -> {
            String traceId = UUID.randomUUID().toString().replace("-", "");
            MDC.put(TRACE_ID, traceId);
            exchange.getRequest().mutate().header("traceId", traceId).build();
            return chain.filter(exchange);
        };
    }

}
