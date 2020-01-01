package com.l.dev.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestStatisticsInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger(RequestStatisticsInterceptor.class);
        LOGGER.info("Logger " + LOGGER.getName() + " is init.");
    }

    public RequestStatisticsInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("Method: " + request.getMethod());
        LOGGER.info("Path: " + request.getRequestURI());
        return super.preHandle(request, response, handler);
    }
}
