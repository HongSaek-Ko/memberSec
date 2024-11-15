package com.example.memberSec.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

// (권한 부족 등으로) 접근 불가 소스 요청 시 handling 하는 class
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override // AccessDeniedHandler의 추상 메서드 handle 존재 - 오버라이드 필요
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 접근 거부 시 처리할 내용 작성
        String deniedUrl = "/accessDenied?exception=" + accessDeniedException.getMessage();
        response.sendRedirect(deniedUrl); // 해당 URL 경로로 강제 이동
    }
}
