package com.example.memberSec.config;

import com.example.memberSec.security.CustomAccessDeniedHandler;
import com.example.memberSec.security.CustomLoginFailureHandler;
import com.example.memberSec.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.logging.Filter;


@Configuration // '설정을 담당하는 클래스'. spring 환경설정과 관련된 spring bean으로 등록하는 annotation
@EnableWebSecurity // 모든 요청 URL이 spring security의 제어를 받도록 만드는 annotation
// 내부적으로 SpringSecurityFilterChain이 동작하여, URL 필터가 적용됨
public class SecurityConfig {

    @Autowired // 의존성 자동 주입
    private UserDetailsService userDetailsService; // remember-me 기능 사용 시 필요

    @Bean // '이 메서드가 리턴하는 객체를 spring bean으로 등록함'
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http // 람다식 사용:         매개변수        요청 객체...
            .authorizeHttpRequests((requests) -> requests
                // .requestMatchers(new AntPathRequestMatcher("/**")).hasAnyRole("ROLE_MEMBER", "ROLE_ADMIN").anyRequest().permitAll() 이렇게도 가능!
                .requestMatchers("/", "/new", "/login", "/test", "/aop").permitAll() // 요청 매칭: 이 "/경로"로 들어오는 요청은 모두 허용하고,
                .anyRequest().authenticated())  // 그 외에 어떠한 요청도 권한이 있어야((로그인)인증을 받아야) 접근 가능
        .csrf((csrf) -> csrf.disable()) // csrf 기능 끄기 (개발 시에만 활용. POST 요청 들어갈 때는 항상 있어야 하나, 개발 시에는 번거로우니까 끔)
        .formLogin((formLogin) -> formLogin
            .loginPage("/login")
            // .usernameParameter("username") 유저의 이름 기본값을 "username"으로 설정
            // .passwordParameter("password") 유저의 암호 기본값을 "password"으로 설정
            .successHandler(new CustomLoginSuccessHandler())
            .failureHandler(new CustomLoginFailureHandler()))
            // .defaultSuccessUrl("/")) // 로그인처리 성공 시 이동할 기본 경로(Url) (= 홈)
        .logout(logout -> logout
                .logoutSuccessUrl("/") // 로그아웃 처리 성공 시 이동할 경로 (= 홈)
                .invalidateHttpSession(true)) // 세션 삭제
        .rememberMe(rememberMeConfig -> rememberMeConfig
                // .rememberMeParameter("remember-me") 로그인 시 자동로그인 기능으로 넘어오는 파라미터명 (html -- checkbox의 name 속성값)
                .tokenValiditySeconds(3600) // 초단위 유효기간 설정, 자동로그인 후 F12 - application - cookie에서 확인 가능
                // .alwaysRemember(false) 체크하지 않아도 항상 자동로그인 설정하는지 여부. 기본값 false
                .userDetailsService(userDetailsService)) // 인증처리
        .exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler()));

        return http.build(); // ... 이렇게 설정한대로 build 처리!
    }

    // securiry 체크 리소스 제외: css, js, img (*: 바로 하위 경로 / **: 그 하위 경로 전체)
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**");
    }

    @Bean // spring bean으로 비밀번호 암호화 시 사용할 클래스 등록
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 구현 클래스
    }

    @Bean // security 인증 담당. 구현한 UserDetailService 클래스 사용
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


}
