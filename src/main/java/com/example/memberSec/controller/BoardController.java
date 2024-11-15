package com.example.memberSec.controller;

import com.example.memberSec.dto.BoardDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    // 게시글 목록 요청
    @GetMapping("/list")
    public String list() {
        return "board/list";
    }

    // 게시글 등록 요청
    @PreAuthorize("isAuthenticated()") // PreAuth: 메서드 호출 전 검증; 로그인해야 요청 가능
    @GetMapping("/new")
    public String newForm() {
        return "board/newForm";
    }

    // 게시글 등록 처리 요청
    @PreAuthorize("isAuthenticated()") // PreAuth: 로그인해야 요청 가능
    @PostMapping("/new")
    public String newFormPro() {
        return "redirect:/board/list";
    }

    // 게시글 상세(본문) 페이지 요청
    @PreAuthorize("isAuthenticated()") // PreAuth: 로그인해야 요청 가능
    @GetMapping("/{bid}")
    public String detail(@PathVariable("bid") String bid) {
        return "board/detail";
    }

    // 게시글 수정 페이지 요청
    @GetMapping("/modify/{bid}")
    @PreAuthorize("isAuthenticated()") // PreAuth: 로그인해야 요청 가능
    public String modifyForm(@PathVariable("bid") String bid, BoardDTO boardDTO) {
        return "board/modifyForm";
    }

    // 게시글 수정 처리 요청
    @PreAuthorize("principal.username == #boardDTO.author()") // 로그인한 사용자(p.u.)와 전달된 데이터(DTO)-'author'가 동일해야 접근 허용
    @PostMapping("/modify/{bid}")
    public String modifyFormPro(@PathVariable("bid") String bid, BoardDTO boardDTO) {
        return "redirect:/board/{bid}";
    }

    // 게시글 삭제 요청
    @PostMapping("/delete/{bid}")
    @PreAuthorize("principal.username == #author()") // 상동
    public String delete(@PathVariable("bid") String bid, String author) {
        return "redirect:/board/list";
    }

}
