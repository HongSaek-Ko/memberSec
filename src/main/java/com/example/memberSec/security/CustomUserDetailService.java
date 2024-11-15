package com.example.memberSec.security;

import com.example.memberSec.dto.MemberDTO;
import com.example.memberSec.repository.MemberRepository;
import com.example.memberSec.security.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO findMember = memberRepository.findByUsername(username);
        if(findMember == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUser(findMember); // userDetails 인터페이스 ← User 클래스 ← CustomUser 자식
    }
}
