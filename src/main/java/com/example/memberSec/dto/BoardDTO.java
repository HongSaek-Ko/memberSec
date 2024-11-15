package com.example.memberSec.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String author;
}
