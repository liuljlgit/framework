package com.cloud.frame.framesecurity.config;

import com.cloud.frame.framesecurity.handler.AuthExceptionEntryPoint;
import com.cloud.frame.framesecurity.handler.CustomAccessDeniedHandler;
import com.cloud.frame.framesecurity.handler.PermissionAccessDecisionVoter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置oauth2.0资源服务器
 * @author lijun
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties({IgnoreUrl.class})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static List<String> ignoreUris = Lists.newArrayList();

    @Autowired
    private IgnoreUrl ignoreUrl;

    @Autowired
    private AccessDecisionVoter accessDecisionVoter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        List<String> uri = new ArrayList<>(ignoreUrl.getUri());
        uri.addAll(ignoreUris);
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
        http.csrf().disable()
            .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())//不存在access_token时候响应
            .and()
                .authorizeRequests()
                .antMatchers(uri.toArray(new String[uri.size()])).permitAll()//忽略鉴权的uri
                .accessDecisionManager(new UnanimousBased(Lists.newArrayList(webExpressionVoter,accessDecisionVoter)))//权限拦截投票器(可以加自定义权限拦截器)
                .anyRequest()
                .authenticated()
            .and()
            .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())//oauth2异常处理
                .accessDeniedHandler(new CustomAccessDeniedHandler());//鉴权失败异常处理
    }

    /**
     * 权限拦截器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(AccessDecisionVoter.class)
    public AccessDecisionVoter accessDecisionVoter(){
        return new PermissionAccessDecisionVoter();
    }

}

