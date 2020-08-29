package com.cloud.frame.framesecurity.config;

import com.cloud.frame.framesecurity.feign.SecurityFeign;
import com.cloud.frame.framesecurity.handler.AccessDeniedExceptionHandler;
import com.cloud.frame.framesecurity.handler.AuthExceptionHandler;
import com.cloud.frame.framesecurity.voter.PermissionAccessDecisionVoter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@EnableConfigurationProperties({IgnoreUrl.class})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    String resourceId;

    @Autowired
    IgnoreUrl ignoreUrl;

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
                .antMatchers(uri.toArray(new String[uri.size()])).permitAll()   //忽略认证的uri,只是认证忽略，投票器还是会进行拦截
                .accessDecisionManager(accessDecisionManager())                 //权限拦截投票器(可以加自定义权限拦截器)
                .anyRequest()
                .authenticated()
            .and()
            .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore)                                    //tokenStore:资源服务器进行token校验
                .authenticationEntryPoint(new AuthExceptionHandler())       //401认证失败处理器
                .accessDeniedHandler(new AccessDeniedExceptionHandler());   //403鉴权失败处理器
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
        return new PermissionAccessDecisionVoter(redisTemplate,securityFeign,resourceId,ignoreUrl);
    }

}

