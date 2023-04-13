package hello.core.singleton;

import hello.core.AppConfigure;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;


public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer() {
        // 스프링없는 순수한 DI컨테이너는 요청할때마다 객체를 생성하여
        // 트래픽이 높을때 비효율적임
        // 해결방법 객체는 한개 생성되고 공유함
        AppConfigure appConfigure = new AppConfigure();
        //조회 :클라이언트가 서비스를 호출할때마다 객체를 생성
        MemberService memberService1 = appConfigure.memberService();
        MemberService memberService2 = appConfigure.memberService();
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService1).isNotSameAs(memberService2);
    }

        @Test
        @DisplayName("싱글톤 패턴을 적용한 객체 사용")
        void SingletonServiceTest(){
            SingletonService singletonService1 = SingletonService.getInstance();
            SingletonService singletonService2 = SingletonService.getInstance();

            System.out.println("singletonService1 = " + singletonService1);
            System.out.println("singletonService2 = " + singletonService2);
            assertThat(singletonService1).isSameAs(singletonService2);
            //isSameAs -> ==(참조 주소 비교)
            //isEqulato -> toString()을 비교
            // 스프링컨테이너 사용시 -> 자동적으로 싱글톤 패턴이 됨(객체를 하나생성하여 공유)
      }

    @Test
    @DisplayName("스프링컨테이노와 싱글톤")
    //스프링의 싱글톤 컨테이너는 싱글톤의 모든 단점을 없애줌
    void springContainer() {
//        AppConfigure appConfigure = new AppConfigure();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigure.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        assertThat(memberService1).isSameAs(memberService2);
    }

}

