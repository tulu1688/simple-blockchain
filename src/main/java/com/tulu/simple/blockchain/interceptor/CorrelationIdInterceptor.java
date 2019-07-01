package com.tulu.simple.blockchain.interceptor;

import com.tulu.simple.blockchain.constant.LoggerConstant;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class CorrelationIdInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String xid = request.getHeader(LoggerConstant.CORRELATION_ID_HEADER);

        if (xid != null) {
            ThreadContext.put(LoggerConstant.CORRELATION_ID_LOG_KEY_NAME, xid.trim());
        }

        return true;
    }
}
