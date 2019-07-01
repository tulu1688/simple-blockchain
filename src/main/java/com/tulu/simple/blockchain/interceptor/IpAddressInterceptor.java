package com.tulu.simple.blockchain.interceptor;


import com.tulu.simple.blockchain.constant.HttpHeaderConstant;
import com.tulu.simple.blockchain.constant.LoggerConstant;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class IpAddressInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String ipAddress = request.getHeader(HttpHeaderConstant.X_IP_ADDRESS_HEADER);
        ThreadContext.put(LoggerConstant.IP_ADDRESS_LOG_KEY_NAME, ipAddress);

        return true;
    }

}
