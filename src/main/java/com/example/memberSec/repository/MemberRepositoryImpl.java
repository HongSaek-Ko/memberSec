package com.example.memberSec.repository;

import com.example.memberSec.dto.AuthDTO;
import com.example.memberSec.dto.MemberDTO;
import com.example.memberSec.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Repository 역할을 하는 클래스로, spring bean으로 등록
@RequiredArgsConstructor
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberMapper memberMapper;

    @Override
    public int save(MemberDTO member) {
        return memberMapper.insertMember(member);
    }

    @Override
    public int addAuth(AuthDTO auth) {
        return memberMapper.insertAuth(auth);
    }

    @Override
    public MemberDTO findByUsername(String username) {
        MemberDTO memberDTO = memberMapper.selectMemberByUsername(username);
        log.info("repositoryImpl findByUsername: {}", memberDTO);
        return memberDTO;
    }

    @Override
    public List<MemberDTO> findAll() {
        return memberMapper.selectAllMembers();
    }

    @Override
    public int updateMember(MemberDTO member) {
        return memberMapper.updateMember(member);
    }

    @Override
    public int deleteAuth(String username) {
        return memberMapper.deleteAuth(username);
    }

    @Override
    public int deleteMember(String username) {
        return memberMapper.deleteMember(username);
    }
}
