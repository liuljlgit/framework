package com.cloud.frame.framesecurity.config;

import com.cloud.frame.framesecurity.feign.SecurityFeign;
import com.cloud.frame.framesecurity.handler.AccessDeniedExceptionHandler;
import com.cloud.frame.framesecurity.handler.AuthExceptionHandler;
import com.cloud.frame.framesecurity.voter.PermissionAccessDecisionVoter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置oauth2.0资源服务器
 * @author lijun
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties({IgnoreUrl.class,ServerConfig.class})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    IgnoreUrl ignoreUrl;

    @Autowired
    ServerConfig serverConfig;

    @Autowired
    AccessDecisionVoter accessDecisionVoter;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    SecurityFeign securityFeign;

    @Resource(name = "tokenStore")
    TokenStore tokenStore;

    @Override
    @SuppressWarnings("unchecked")
    public void configure(HttpSecurity http) throws Exception {
        List<String> uri = new ArrayList<>(ignoreUrl.getUri());
        http.csrf().disable()
            .exceptionHandling().authenticationEntryPoint(new AuthExceptionHandler())//不存在access_token时候响应
            .and()
                .authorizeRequests()
                .antMatchers(uri.toArray(new String[uri.size()])).permitAll()   //忽略鉴权的uri
                .accessDecisionManager(accessDecisionManager())                 //权限拦截投票器(可以加自定义权限拦截器)
                .anyRequest()
                .authenticated()
            .and()
            .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .tokenStore(tokenStore)
                .authenticationEntryPoint(new AuthExceptionHandler())//oauth2异常处理
                .accessDeniedHandler(new AccessDeniedExceptionHandler());//鉴权失败异常处理
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());
        RoleVoter roleVoter = new RoleVoter();
        AuthenticatedVoter authenticatedVoter = new AuthenticatedVoter();
        return new UnanimousBased(Lists.newArrayList(
                webExpressionVoter,
                roleVoter,
                authenticatedVoter,
                accessDecisionVoter));
    }

    //自定义权限拦截器
    @Bean
    @ConditionalOnMissingBean(AccessDecisionVoter.class)
    public PermissionAccessDecisionVoter accessDecisionVoter(RedisTemplate<String,Object> redisTemplate,SecurityFeign securityFeign){
        return new PermissionAccessDecisionVoter(redisTemplate,securityFeign,serverConfig.getName());
    }

}

