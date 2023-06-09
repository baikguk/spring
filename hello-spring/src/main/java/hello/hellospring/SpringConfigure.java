package hello.hellospring;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// 컴포넌트 말고 자바로 빈등록하는 방법 @Service @Autowired가 동시에 되는 느낌
            //컨트롤러는 컴포넌트 스캔으로 해야함
public class SpringConfigure {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfigure(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    private EntityManager em;
//    @Autowired
//    public SpringConfigure(EntityManager em) {
//        this.em = em;
//    }


//    private DataSource dataSource; // 데이터 베이스와 연결할 수 있는 정보
//    @Autowired
//    public SpringConfigure(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    //    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource); // 자바의 다형성을 통해 쉽게 변경 가능 (assembly 조립)
//        return new JpaMemberRepository(em);
//    }
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
