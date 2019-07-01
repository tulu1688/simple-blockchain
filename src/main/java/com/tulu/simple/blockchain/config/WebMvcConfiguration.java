package com.tulu.simple.blockchain.config;

import com.tulu.simple.blockchain.interceptor.CorrelationIdInterceptor;
import com.tulu.simple.blockchain.interceptor.IpAddressInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private CorrelationIdInterceptor correlationIdInterceptor;

    @Autowired
    private IpAddressInterceptor ipAddressInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(correlationIdInterceptor).addPathPatterns("/**");
        registry.addInterceptor(ipAddressInterceptor).addPathPatterns("/**");
    }
}
