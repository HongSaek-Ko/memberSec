package com.example.memberSec.controller;

import com.example.memberSec.dto.MemberDTO;
import com.example.memberSec.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/testuser/{path}")
    public String list(HttpServletRequest request, @PathVariable(name = "path") String path) {
        HttpSession session = request.getSession();
        session.setAttribute("user", memberService.getMember("test1"));
        return "redirect:/" + path;
    }

    // 회원 목록 요청
    @GetMapping
    public String list(Model model) {
        List<MemberDTO> members = memberService.getMembers();
        model.addAttribute("members", members);
        return "members/list";
    }

    // 마이페이지 요청
    @GetMapping("/{username}")                                      // 데이터 전달
    public String mypage(@PathVariable("username") String username, Model model) {
        log.info("마이페이지: {}", username);
        MemberDTO member = memberService.getMember(username);
        model.addAttribute("member", member);
        // model.addAttribute("member", user.getMember()); 다 생략하고 이걸로 써도 문제 없음 (로그인한 상태에서만 접근 가능하고, principal로부터 꺼내다 쓰기만 하면 되니까)
        return "members/mypage";
    }

    // 회원정보 수정 폼 요청
    @GetMapping("/modify/{username}")
    public String modifyPage(@PathVariable("username") String username, Model model) {
        log.info("modifyPage: {}", username);
        MemberDTO member = memberService.getMember(username);
        model.addAttribute("member", member);
        return "members/modify";
    }

    // 정보 수정 처리 요청
    @PostMapping("/modify/{username}")
    public String modify(@PathVariable("username") String username, MemberDTO member) {
        log.info("modify: {}", username);
        log.info("modify: {}", member);

        member.setUsername(username);
        memberService.updateMember(member);
        return "redirect:/members/{username}";
    }

    // 회원 삭제 요청
    @PostMapping("/delete/{username}")
    public String delete(@PathVariable("username") String username, HttpSession session) {
        log.info("delete: {}", username);
        memberService.deleteMember(username);
        /* 로그아웃 처리
         - 1. 단순 로그아웃 - /logout GET 이면 redirect:/logout (spring security 로그아웃은 default가 POST) // 제한됨
         - 2. 우회; 인증정보 삭제 - SecurityContextHolder 활용 */
        SecurityContextHolder.clearContext(); // SecurityContext(인증정보 저장소) 초기화 == 로그아웃
        session.invalidate(); // 세션 속성 모두 삭제 - 세션에 저장된 Authentication 정보 삭제


//        String sid = (String) RequestContextHolder.currentRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
//        HttpServletRequest request = (HttpServletRequest) RequestContextHolder.getRequestAttributes();
//        Cookie[] cookies = request.getCookies();
//        HttpSession session = request.getSession();

        return "redirect:/"; // 회원삭제 후 홈으로 강제이동
    }
}
