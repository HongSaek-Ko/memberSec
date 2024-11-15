package com.example.memberSec.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("requestURI: {}", requestURI);

        /*
        request.getSession().setAttribute("data", "hello"); // 세션에 속성 추가
        HttpSession session = request.getSession();
        Object springSecurityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (springSecurityContext == null) {
            log.info("SPRING_SECURITY_CONTEXT is null(로그인 안함)");
            response.sendRedirect("/login");
            return false; // '다음 인터셉터 진행' 취소!
        } */

        return true; // 다음 인터셉터 이어서 실행
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
