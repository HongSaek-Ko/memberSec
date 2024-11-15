package com.example.memberSec.service;

import com.example.memberSec.dto.AuthDTO;
import com.example.memberSec.dto.MemberDTO;
import com.example.memberSec.repository.MemberRepository;
import com.example.memberSec.security.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional // '트랜젝션 구간이 여기부터 시작함'
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int register(MemberDTO member, String au) {
        // 비밀번호 암호화
        String encodePw = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodePw); // 암호화된 비밀번호로 변경 $2a$10$6Pz.M5v5TyIvu... 이런식으로 출력됨

        // 회원 정보 저장
        int memberRes = memberRepository.save(member);
        int authRes = 0;

        // 권한 부여 (MEMBER)
        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(member.getUsername());

        authDTO.setAuth("ROLE_MEMBER");
        authRes = memberRepository.addAuth(authDTO);

        // 권한 "'추가'" 부여 (ADMIN)
        if(au.equals("admin")){ // au가 "admin"일 경우.
            authDTO.setAuth("ROLE_ADMIN"); // MEMBER와 추가로 ADMIN이 설정됨
            authRes = memberRepository.addAuth(authDTO); //
        }

        return (memberRes == 1 && authRes == 1) ? 1 : 0;
    }

    @Override
    public MemberDTO getMember(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public List<MemberDTO> getMembers() {
        return memberRepository.findAll();
    }


    @Override
    public int updateMember(MemberDTO member) {
        return memberRepository.updateMember(member);
    }



    @Override
    public int deleteMember(String username) {
        memberRepository.deleteAuth(username);
        int result = memberRepository.deleteMember(username);
        return result;
    }
}
