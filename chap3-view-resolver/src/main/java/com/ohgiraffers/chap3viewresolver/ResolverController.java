package com.ohgiraffers.chap3viewresolver;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model) {
        model.addAttribute("sendMessage", "문자열로 뷰 이름 반환");
        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect(Model model) {
        //접두사로 redirect: 를하면 redirect 시킨다
        return "redirect:/";
    }

    @GetMapping("string-redirect-attr")
    public String stringRedirectAttr(RedirectAttributes redirectAttributes) {
        /*
        * 리다이렉트 시 flash 영역에 담아서 redirect 할 수 있다 flash - 1회용 세션
        * 자동으로 모델에 추가되기 때문에 requestScope 에서 값을 꺼내면 된다
        * 세션에 임시로 값을 담고 소멸하는 방식이기 때문에 session에 동일한 키 값이 존재하지 않아야한다*/
        redirectAttributes.addFlashAttribute("flashMessage1","redirect attr 사용하여 redirect");
        return "redirect:/";
    }
    /*
    * PRG패턴 post redirect get
    * 서버가 post로 받은 데이터를 처리한 후 리다이렉트 응답을 클라이언트에게 보낸다
    * 클라이언트는 리다이렉트 된 URL로 get 요청을 보내고 그 결과를 화면에 표시한다
    * 이렇게 되면 이후에 새로고침시에도 get요청을 보내기 떄문에 중복 데이터 처리가 발생하지 않는다*/

    /*
    * ModelAndView - spring 에서 모델과 뷰를 함꼐 처리하기 위한 클래스
    * */
    @GetMapping("modelandview")
    public ModelAndView modelAndView(ModelAndView mv) {
        mv.addObject("sendMessage","modelandview를 이용한 모델과 뷰 반환");
        mv.setViewName("result");
        return mv;
    }
    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {
        mv.addObject("flashMessage2","test");
        mv.setViewName("redirect:/");
        return mv;
    }
    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectAttr(ModelAndView mv,RedirectAttributes rttr) {
        rttr.addFlashAttribute("flashMessage2","ModelAndView 를 이용한 attr");
        mv.setViewName("redirect:/");
        return mv;
    }
}
