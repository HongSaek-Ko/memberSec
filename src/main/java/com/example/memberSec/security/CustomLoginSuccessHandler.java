package com.example.memberSec.security;

import com.example.memberSec.dto.MemberDTO;
import com.example.memberSec.security.domain.CustomUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("로그인 성공!");
        /*
        // request 객체가 있으므로 아래가 가능...
        Cookie[] cookies = request.getCookies(); // 쿠키 조회
        HttpSession session = request.getSession(); // 세션 꺼내기
        String name = request.getParameter("name"); // 파라미터 조회
        request.setAttribute("msg", "success"); // request에 속성 추가 (= model.addAttribute와 비슷한 효과)

        // authentication
        CustomUser user = (CustomUser) authentication.getPrincipal(); // 사용자 정보 조회
        MemberDTO member = user.getMember(); //
        List<String> roleNames = new ArrayList<>();
        authentication.getAuthorities().forEach(auth -> {
            roleNames.add(auth.getAuthority());
        });

        if(roleNames.contains("ROLE_ADMIN")){
            response.sendRedirect("/admin");
            return;
        }
        if(roleNames.contains("ROLE_MEMBER")){
            response.sendRedirect("/member");
            return;
        }

        // response 객체
        response.addCookie(new Cookie("cid", member.getUsername())); // 쿠키 추가
        response.setCharacterEncoding("UTF-8"); // 응답 데이터 인코딩 설정
        response.sendRedirect("/members"); // 페이지 강제 이동
        */



    }
}
