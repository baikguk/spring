package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 롬복의 어노테이션 -> 필수값(final) 을 파라미터로 받는 생성자를 만들어줌
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; //생성자 주입 방식에서는 final을 통해 불변,및 누락 유지할 수 있음
    private final DiscountPolicy discountPolicy;
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    @Autowired
//     ㄴ 생성자가 하나면 생략 가능 , Autowired는 타입으로 조회함 (ex ac.getBean(DiscountPolicy.class)
//                                             ㄴ 이 과정에서 fix,와 rate가 둘다 빈으로 등록 되어있으면 어떤것을 부를지에 대해
//                                                    문제가 생김
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
//    ㄴ 롬복으로 자동으로 생성자를 만들시 주석처리 할수 있음

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
