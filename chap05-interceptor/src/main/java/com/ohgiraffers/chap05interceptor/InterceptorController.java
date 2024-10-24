package com.ohgiraffers.chap05interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorController {

    @Autowired
    private InterceptorService interceptorService;

    @PostMapping("stopwatch")
    public String handlerMethod(Model mmdel) throws InterruptedException {
        mmdel.addAttribute("test", "모델 테스트");
        System.out.println("핸들러 메소드 호출함");
        interceptorService.method();
        Thread.sleep(1000);
        return "result";
    }
}
