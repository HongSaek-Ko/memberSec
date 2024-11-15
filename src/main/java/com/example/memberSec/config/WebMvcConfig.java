package com.example.memberSec.config;

import com.example.memberSec.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// interceptor 적용 시 WebMvcConfigurer 구현하는 설정 class 작성
// @Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") // 적용할 경로 패턴 (*: 바로 하위 경로 / **: 그 하위 경로 전체)
                .excludePathPatterns("/", "/signup", "/login"); // 제외시킬 경로들
        // 추가할 인터셉터 이곳에 추가...
        // registry.addInterceptor(new XxxInterceptor())
        //          .order(2)
        //          . ...
    }
}
