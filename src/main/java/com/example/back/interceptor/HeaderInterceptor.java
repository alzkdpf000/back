package com.example.back.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request.getRequestURI() : {}",request.getRequestURI());
        String target = request.getRequestURI();
//        String contextPath = request.getContextPath();
        log.info("request.getContextPath() : {}",request.getContextPath());
//        String target = request.getRequestURI().replace(contextPath, "/");
//        log.info("target : {}",target);
        String category = switch(target) {
            case "/" -> "home";
            case "/post/list" -> "post";
            case "/doctor/list" -> "doctor";
            case "/house-call/reg" -> "house-call-reg";
            case "/house-call/list" -> "house-call-list";
            case "/rank" -> "rank";
            case "/payment" -> "payment";
            default -> null;
        };
        request.setAttribute("category",category);
        return true;
    }
}
