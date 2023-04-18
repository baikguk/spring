package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // rate와 빈 충돌을 위해 해줌
            //.NoUniqueBeanDefinitionException 발생
            //해결 방법 1 : OrderServiceIpl의 파리미터 이름을 구현채 이름으로 바꾸면
        //                  충돌시 파라미터의 이름 or 필드이름의 구현체를 사용함
        // 해결 방법 2 : @Qualifer ->구분자자 끼리 매칭 -> 없으면 빈의 이름을 매칭 -> 없으면 예외
        // 해결방법3 : Primary 우선순위를 부여e
//     CF) Qualifier과 Primary를 동시에 ㅅ용하면 상대적으로 범위가 적은 qualifier가 먼저 작동(스ㅍ링은 수동, 범위가 좁은게 항상 먼저작동)
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount =1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }else {
            return 0;
        }
    }
}
