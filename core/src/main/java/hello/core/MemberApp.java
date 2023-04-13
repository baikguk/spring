package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfigure appConfigure =new AppConfigure();
//        MemberService memberService = appConfigure.memberService();

        //┏ 스프링 생성 및 시작 (스프링 컨테이너 -> 객체 관리)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigure.class);

        //                                  ┌ 생성한 컨테이너에서 이름에 해당하는 bean을 꺼냄
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //위 주석과 동일한 기능 차이는 컨테이너에서 꺼내다 씀

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
