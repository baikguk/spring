package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // ┌ 스캔 시작위치를 설정 -> 멤버패키지에만 해당하는 Componert만 빈에 등록함
        // 디폴로는 @Component가 등록된 패키지가 시작위치가됨
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // ㄴ 기존 @Configure 붙은 것은e 제외
)
// @Component를 찾아 빈으로 등록해줌
public class AutoAppConfigure {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
