package com.example.memberSec.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SampleServiceImpl implements SampleService{

    // 단순히 문자열 타입의 숫자 두 개 더한 결과 리턴
    @Override
    public Integer doAdd(String str1, String str2) {
        log.info("doAdd 실행!");
        for(int i = 0; i < 100; i++) {
            log.info("{}", i);
        }
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}
