package com.ohgiraffers.chap04exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //Exception 이 발생했을때 핸들링 해주는 클래스를 만드는 어노테이션
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointer(NullPointerException e) {
        System.out.println("global 레벨  서버에 발생하는 모든 레벨의 exception 처리");
        return "error/nullPointer";
    }
    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistException(MemberRegistException e, Model model) {
        System.out.println("global 레벨 서버에 발생하는 모든 레벨의 exception 처리");
        model.addAttribute("exception", e);
        return "error/memberRegist";
    }

}
