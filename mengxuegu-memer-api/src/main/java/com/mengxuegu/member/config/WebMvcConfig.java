package com.mengxuegu.member.config;

import com.mengxuegu.member.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationFilter)
                //拦截所有请求
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login");
    }
}
