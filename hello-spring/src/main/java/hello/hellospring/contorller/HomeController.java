package hello.hellospring.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // '/' 하나면 처음 실행했을 때의 페이지
                //기존에 home페이지로 정적페이지인 index.html을 만들었엇지만
                // Controller가 우선순위가 있기 때문에 home.html으로 불러짐
    public String home() {
        return "home";
    }
}
