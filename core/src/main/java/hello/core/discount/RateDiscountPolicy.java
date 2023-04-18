package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //빈 충돌시 우선 순위 부여
//@Qualifier("mainDiscountPolicy")
//  ㄴ 위방법은 문자열로 이름을 부여하기 때문에 오타로 인한 오류 발생가능성있음
@MainDiscountPolicy //이를 방지하기 위해 어노테이션을 만들어서 사용
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountRate / 100;
        } else return 0;
    }
}
