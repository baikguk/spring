package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfigure {//구성영역을 만들어 줌으로써
    //샤용영역인 order,member,discount는 자기 기능만 충실하게됨
    // DIP를 지키게 됨
    // 정적인 의존관계인 사용영역(클래스 의존관계)
    //동적인 의존관계인 구셩엉역(객체 의존관계)
    //사용자가 프로그램 흐름 제어 -> 라이브러리
    //IOC(제어의 역전) ->프레임워크,AppConfigure, Junit,, IOC컨테이너=DI컨테이너
    //AppConfigure  같은 역할을 ( assembler(조립자), Object Factory(생성자 공장)이라고도함
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        //  ㄴ생성자 주입
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
