package com.ohgiraffers.chap04exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerComtroller {

    @GetMapping("/controller-null")
    public String nullpointExceptionTest(){

        String str = null;
        System.out.println(str.charAt(0));
        return "main";
    }

    //Exception 처리의 우선권을 가진다
    @ExceptionHandler(NullPointerException.class)
    public String nullpointerExceptionhandler(NullPointerException e){
        System.out.println("controller 레벨의 exception 처리");
        return "error/nullPointer";
    }
    @GetMapping("controller-user")
    public String userException() throws MemberRegistException {
        boolean check = true;
        if(check){
            System.out.println("hi");
            throw new MemberRegistException("입사가 불가능함");
        }
        return "/";
    }
    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(MemberRegistException e, Model model) {
        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", e);
        return "error/memberRegist";
    }
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        System.out.println("나머지 exception");
        return "error/default";
    }
}
