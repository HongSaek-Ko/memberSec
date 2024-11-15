package com.example.memberSec.service;

import com.example.memberSec.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    // 회원가입
    public int register(MemberDTO member, String au);
    // 회원 1명 조회
    public MemberDTO getMember(String username);
    // 회원 목록 조회
    public List<MemberDTO> getMembers();
    // 회원 정보 수정
    public int updateMember(MemberDTO member);
    // 회원 정보 삭제
    public int deleteMember(String username);
}
