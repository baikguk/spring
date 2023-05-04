package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// 바이트코드를 조족하는 CGLIB기술로 싱글 톤보장
            //어노테이션을 주석처리하면 원래 자바 코드 식으로 되고
            // memeberReposotiry가 3번 호출됨 -> MemorymemberRepository를 3번 만들게됨
            // @Configuration을 사용하지 않으면 싱글톤을 보장하지 않는다

public class AppConfigure {//구성영역을 만들어 줌으로써
    //샤용영역인 order,member,discount는 자기 기능만 충실하게됨
    // DIP를 지키게 됨
    // 정적인 의존관계인 사용영역(클래스 의존관계)
    //동적인 의존관계인 구셩엉역(객체 의존관계)
    //사용자가 프로그램 흐름 제어 -> 라이브러리
    //IOC(제어의 역전) ->프레임워크,AppConfigure, Junit,, IOC컨테이너=DI컨테이너
    //AppConfigure  같은 역할을 ( assembler(조립자), Object Factory(생성자 공장)이라고도함

   //  싱글 톤이지만
   //  MemerService의 return new MemberServiceImpl(memberRepository());
   // OrderServiced의return new OrderServiceImpl(memberRepository(), discountPolicy());
   //   으로 인해  memberRepository()가 두번 생성할것 같지만
    //  soutm으로 메소드 호출 프린트 해서 테스트에서 확인하면
    // memberRepsoitory 한번만 호출함 -> 자바언어적으로 이상함
    // @Configuration때문에 그럼
   //review
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfigure.memberService");
        return new MemberServiceImpl(memberRepository());
        //  ㄴ생성자 주입
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfigure.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfigure.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
