package com.cloud.frame.framesecurity.filter;

import com.alibaba.fastjson.JSON;
import com.cloud.ftl.ftlbasic.webEntity.CodeEnum;
import com.cloud.ftl.ftlbasic.webEntity.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Order(-999)
@Component
@ConditionalOnProperty(prefix = "explogin", name = "enable" , havingValue = "true")
@WebFilter(filterName = "expLoginRequestFilter" ,urlPatterns = "/**")
public class ExpLoginRequestFilter implements Filter {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Value("${explogin.url:}")
    private String expLoginUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HeaderMapRequestWrapper headerMapRequestWrapper = new HeaderMapRequestWrapper((HttpServletRequest) request);
            String encryptStr = headerMapRequestWrapper.getHeader("encryptStr");
            if(StringUtils.isEmpty(encryptStr)){
                //如果没带了加密串，证明不是appserver请求，直接走系统鉴权就可以了。
                filterChain.doFilter(request,response);
            } else {
                //这里把请求头删除掉，减少在服务调用的时候进行判断
                headerMapRequestWrapper.addRhs("encryptStr");
                TokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
                DefaultTokenServices tokenServices = new DefaultTokenServices();
                tokenServices.setTokenStore(tokenStore);
                String accessToken = redisTemplate.opsForValue().get("EXT:APPSERVER:ACCESSTOKEN:" + encryptStr);
                if(!StringUtils.isEmpty(accessToken)){
                    OAuth2AccessToken oAuth2AccessToken = tokenServices.readAccessToken(accessToken);
                    if(Objects.nonNull(oAuth2AccessToken) && oAuth2AccessToken.isExpired()){
                        accessToken = restTemplate.getForObject(expLoginUrl + encryptStr, String.class);
                        redisTemplate.opsForValue().set("EXT:APPSERVER:ACCESSTOKEN:" + encryptStr , accessToken);
                    }
                } else {
                    accessToken = restTemplate.getForObject(expLoginUrl + encryptStr, String.class);
                    redisTemplate.opsForValue().set("EXT:APPSERVER:ACCESSTOKEN:" + encryptStr , accessToken);
                }
                if(!StringUtils.isEmpty(accessToken)){
                    headerMapRequestWrapper.addAhm("Authorization","Bearer " + accessToken);
                    //如果登录成功，没有异常，则放行
                    filterChain.doFilter(headerMapRequestWrapper,response);
                } else {
                    writeResponseMsg(response, CodeEnum.EXEC_ERROR.getCode(),"无法获取accessToken，认证失败！");
                }
            }
        } catch (Exception e){
            log.error("AppServerRequestFilter拦截，登录失败!", e);
            writeResponseMsg(response, CodeEnum.EXEC_UNAUTHORIZED_401.getCode(),"登录失败");
        }
    }

    private void writeResponseMsg(ServletResponse response,Integer code,String msg){
        try {
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.setContentType("application/json;charset=utf-8");
            httpResponse.getWriter().write(JSON.toJSONString(RespEntity.error(code,msg)));
            httpResponse.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
