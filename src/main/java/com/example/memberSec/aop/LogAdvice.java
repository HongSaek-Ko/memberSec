package com.example.memberSec.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect  // 해당 클래스가 Aspect를 구현한 것임을 선언
@Component // 스프링빈으로 등록
@Slf4j
public class LogAdvice {

    /*
    @Before("execution(* com.example.memberSec.service.SampleService*.*(..))")
    public void logBefore() {
        log.info("---------------------- aop before -----------------------");
    }

    @After("execution(* com.example.memberSec.service.SampleService*.*(..))")
    public void logAfter() {
        log.info("---------------------- aop after -----------------------");
    }

    @AfterReturning("execution(* com.example.memberSec.service.SampleService*.*(..))")
    public void logAfterReturning() {
        log.info("---------------------- aop after returning ----------------------");
    }

    @AfterThrowing(pointcut = "execution(* com.example.memberSec.service.SampleService*.*(..))",
        throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        log.info("---------------------- aop after throwing exception---------------------");
        log.info("exception: {}", exception.getMessage());
    }
    */

    @Around("execution(* com.example.memberSec.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        log.info("Target : {}", joinPoint.getTarget());
        log.info("Parameter : {}", Arrays.toString(joinPoint.getArgs()));
        // BEFORE
        Object result = joinPoint.proceed(); // 전/후를 나누는 기준 -> 원래 가던길 가라~
        // AFTER
        long end = System.currentTimeMillis();
        log.info("Time taken : {}", (end - start));

        return result;
    }



}
