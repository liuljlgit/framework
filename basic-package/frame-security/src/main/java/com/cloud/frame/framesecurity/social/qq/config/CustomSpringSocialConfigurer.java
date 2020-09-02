package com.cloud.frame.framesecurity.social.qq.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class CustomSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public CustomSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setSignupUrl("/register");
        return (T) filter;
    }
}
