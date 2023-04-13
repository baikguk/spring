package hello.core.singleton;

import hello.core.AppConfigure;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigure.class);

        MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        System.out.println("memberservice->memberRepository1 = " + memberRepository1);
        System.out.println("orederservice->memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        //모두 같은 레지스터를 가르킴 ㄷㄷ

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigure.class);
        AppConfigure bean = ac.getBean(AppConfigure.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        //$$EnhancerBySpringCGLIB가 추가적으로 출력됨
    }

}
