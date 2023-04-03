package hello.hellospring.contorller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
스프링이 시작되면 스프링 컨테이너라는 통이 생김
Contorller annotation이 있으면 해당 객체를 생성해서 컨테이너에 넣어두고 관리함 -> @Component로 한것
    == 스프링컨테이너에서 스프링빈이 관리됨
* */
@Controller
public class MemberContorller {

    private final MemberService memberService; //-> 스프링 컨테이너에 등록만 함 객체 생성 x


    /*
    컨트롤러에서 멤버서비스의 객체를 직접 선언해서 새로 만들기보단 한번 생성해서 공용으로 사용해야 함
    Autowired 어노테이션은 스프링 컨테이너에 있는 MemberSerivce를 어노테이션 밑의 생성자에 연결해서 생성됨
    */
    public MemberContorller(MemberService memberService) {
        this.memberService = memberService;
    }
    /*
    멤버컨트롤러가 생성될때 스프링빈에 등록되어있는 멤버서비스를 객체를 얻어서 생성도미
    이러한게 Dependacy Injection이라 함 -> 의존관계를 주입함
    DI는 생성자 주입말고 2가지가 더있음
    필드주입(@Autowired를 private final MemberService memberService앞에) ->별로 안좋음 세부 설정이 힘들어서
    Setter 주입
    @Autowired
    public void setMemberService(MemberService memberService) -> setter라 public으로 열려있어야하는데
    멤버서비스를 바꿀일이 없어 노출이 됨
    -> 생성자 주입이 최고다~
    * */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // form 태그에서 post방식으로 보낸 action을 받음
    public String create(MemberForm form) {//이후 create 메소드 실행이 됨
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/ ";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);//입력된 데이터는 메모리에 올라가있기 때문에 스프링을 껏다 키면 지워짐 db 필요
        return "members/memberList";
    }
}
