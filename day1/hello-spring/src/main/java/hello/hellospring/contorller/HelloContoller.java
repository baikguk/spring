package hello.hellospring.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloContoller {

    @GetMapping("hello") // web 어플리케이션에서 /hello가 입력되면 해당 메소드를 호출
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }
}
