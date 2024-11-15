package com.example.memberSec.dto;

import lombok.Data;

@Data
public class AuthDTO {
    private String username;
    private String auth; // enum Role -> private Role auth 도 가능
}
