package com.example.memberSec.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // request 객체
        String username = request.getParameter("username"); // 파라미터 조회
        String password = request.getParameter("password");

        // Exception
        String exceptionMessage = exception.getMessage();
        String cause = "로그인 실패";
        if(exception instanceof UsernameNotFoundException ) {
            cause = "no-username"; // 계정 없는 경우
        }else if(exception instanceof BadCredentialsException ) {
            cause = "bad-credentials"; // 비밀번호 불일치 시
        }else if(exception instanceof CredentialsExpiredException) {
            cause = "credentials-expired";
        }else if(exception instanceof AccountExpiredException) {
            cause = "account-expired"; // 계정 만료
        }else if(exception instanceof DisabledException) {
            cause = "disabled"; // 계정 사용 불가
        }else if(exception instanceof LockedException) {
            cause = "locked"; // 계정 잠김
        }else {
            cause = "unknown"; // 알 수 없는 에러
        }


        // response 객체
        response.sendRedirect("/login?fail=true");
    }
}
