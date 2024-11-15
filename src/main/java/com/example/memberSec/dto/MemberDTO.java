package com.example.memberSec.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberDTO {
    private String username;
    private String password;
    private String name;
    private String email;
    private LocalDateTime regDate;
    private String enabled;
    private List<AuthDTO> authlist; // 권한 목록
}
