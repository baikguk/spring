package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService =  new MemberService(memberRepository);
        //테스트를 진행할때마다 새로이 레파지토리 객체가 만들어지는 것이 아닌 외부에서 생성된 객체를
        // 생성자에 넣어줌 -> 이러한 방법을 DI(Dependance injection)이라함
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    
    @Test
    void 회원가입() {//테스트는 한국어도 ㄱㅊ 
        // given :입력
        Member member = new Member();
        member.setName("hello");
        //when : 로직
        Long saveId = memberService.join(member);
        //then: 출력
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        //try-catch 넣기에는 애매하다함
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}