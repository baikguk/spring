package hello.hellospring.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloContoller {

    @GetMapping("hello") // web 어플리케이션에서 /hello가 입력되면 해당 메소드를 호출
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")//MVC 및 템플릿 엔진 방식으로 파라미터가 값을 요구함 @RequestParam의 경우 boolean required=true이기 때문에 무조건 파라미터를 받아야함
    // localhost:8081/hello-mvc?name=spring 을 통해 name에 spring을 넣어줌
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        //view resolver가 반환값에 해당하는 템플릿을 thymleaf 템플린 엔진을 통해 찾고
        // html로 변환 해줌
    }

    @GetMapping("hello-spring")//APi 방식으로 View를 통하지 않고 문자열이 그대로 넘어감 ->페이지 소스를 보면 hellospring값만 있음
    //근데 이렇게는 잘 사용안함
    @ResponseBody// http에서 의 바디부분에 return값을 직접 넣어줌 ->view resolver가 작동안함
    public String helloString(@RequestParam("name") String name) {
        return "hello"+ name; //ex> hello spirng
    }

    @GetMapping("hello-api")// 자주 사용한 APi방식
    //위 결과와 유사하지만 파라미터가 json방식으로 전달됨
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 객체가 반환되면 httpmessageconverter가 객체이면 jsonconvete로 json 데이터로 변환후 반환
        // 문자면 stringconveter가
    }

    static class Hello {
        //밑의 pirvate 변수와 getter setter은 public의 형태로 클래스를 만든형식을 java bean 표주ㅜㄴ방식이라함
        //== property 접근 방식
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }


    }


}
