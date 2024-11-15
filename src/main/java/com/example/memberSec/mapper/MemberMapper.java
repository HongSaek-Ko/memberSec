package com.example.memberSec.mapper;

import com.example.memberSec.dto.AuthDTO;
import com.example.memberSec.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 회원정보 저장
    public int insertMember (MemberDTO member);
    // 회원 권한 추가
    public int insertAuth (AuthDTO auth);
    // 회원 1명 조회
    public MemberDTO selectMemberByUsername (String username);
    // 회원 목록 조회
    public List<MemberDTO> selectAllMembers();
    // 회원 정보 수정
    public int updateMember(MemberDTO member);
    // 회원 권한 삭제
    public int deleteAuth (String username);
    // 회원 정보 삭제
    public int deleteMember (String username);

}
