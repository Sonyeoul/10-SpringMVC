package com.ohgiraffers.chap05interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    /*
    * default메서드가 존재하기 이전에는 모두 오버라이딩을 해야하는 책임을 가지기 때문에
    * JHandlerInterceptorAdaptor를 이용해 사용했으나
    * default 메소드가 인터페이스에 사용 가능하게 된 java8 버전 이후에는 인터페이스만 구현하여
    * 필요한 메서드만 오버라이딩해서 사용 할 수 있다.
    * */
    //전처리 메소드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!request.getParameter("auth").equals("admin")){
            response.sendRedirect("/");
        }
        System.out.println("preHandle호출");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }
    //후처리 메소드 --지정된 컨트롤러의 동작 이후 처리할 동작 제어
    //실행이 완료 되었지만 아직 view가 생성되기 전에 호출됨
    //디스패쳐 서블릿이 화면을 띄우기 전에 동작함
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("모델확인"+modelAndView.getModelMap());
        System.out.println("postHandle 호출함");
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        modelAndView.addObject("interval", endTime - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출함");
    }
}