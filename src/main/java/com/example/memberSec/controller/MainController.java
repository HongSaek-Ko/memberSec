package com.example.memberSec.controller;

import com.example.memberSec.dto.MemberDTO;
import com.example.memberSec.security.domain.CustomUser;
import com.example.memberSec.service.MemberService;
import com.example.memberSec.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final SampleService sampleService;

    // 홈
    @GetMapping("/")
    public String home(@AuthenticationPrincipal CustomUser user, Model model) { // 로그인 시 CustomUser 객체에 해당 principal 정보를 채워줌
        log.info("home");
        log.info("user: {}", user); // user: com.example.memberSec.security.CustomUser [...]
        if(user == null) { // 비로그인(user 객체가 없을 시)
            return "home";
        } model.addAttribute("member", user.getMember()); // MemberDTO → 화면에 전달
        return "loginHome";
    }

    // 회원 가입 폼 요청
    @GetMapping("/new")
    public String newForm(@ModelAttribute("member") MemberDTO member) {
        return "newForm";
    }

    // 회원 가입 처리 요청
    @PostMapping("/new")                // 일반회원 / 관리자 설정하는 파라미터
    public String newPro(MemberDTO member, String au) {
        log.info("newPro - member : {}", member);
        log.info("newPro - au : {}", au);
        // 회원 가입 처리
        int result = memberService.register(member, au);
        log.info("newPro - result : {}", result);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("member") MemberDTO member) {
        log.info("loginForm!!");
        return "login";
    }

    @GetMapping("/test")
    public String testForm(@ModelAttribute("member") MemberDTO member) {
        return "test";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        log.info("접근 거부됨!");
        return "denied";
    }

    @GetMapping("/aop")
    public String aop() {
        Integer result = sampleService.doAdd("10", "20");
        log.info("aop result : {}", result);
        return "home";
    }





}
