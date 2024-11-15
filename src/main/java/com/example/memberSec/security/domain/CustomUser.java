package com.example.memberSec.security.domain;

import com.example.memberSec.dto.MemberDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter // MemberDTO 외부에서 꺼낼 수 있게 Getter 추가
public class CustomUser extends User { // == Principal

    private MemberDTO member; // 알고 있는 회원 정보 DTO 추가

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        // User 클래스는 기본 생성자가 없기 때문에, 부모의 생성자를 호출해줘야 함(super)
        super(username, password, authorities);
    }

    // 외부에서 MemberDTO를 매개변수로 주면, 부모생성자 호출하며 Security用 데이터 세팅, MemberDTO도 채우기
    public CustomUser(MemberDTO member) {
        super(member.getUsername(), member.getPassword(),
                member.getAuthlist()
                        .stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                        .collect(Collectors.toList())
        );
        this.member = member;
    }
}
