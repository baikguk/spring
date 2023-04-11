package hello.core.member;

import hello.core.AppConfigure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfigure appConfigure = new AppConfigure();
        memberService= appConfigure.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA",Grade.VIP) ;

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        //검즘 코드
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
