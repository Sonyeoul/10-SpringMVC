package com.ohgiraffers.chap05interceptor;

import org.springframework.stereotype.Service;

@Service
public class InterceptorService {
    
    public void method(){
        System.out.println("서비스 메서드 호출확인");
    }
}
