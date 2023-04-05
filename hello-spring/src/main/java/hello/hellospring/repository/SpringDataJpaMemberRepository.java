package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {
    //                  ㄴ구현이안된 스프링데이터jpa가 jparespository를 받고있으면 자동으로 구현체를 만들어줌
     @Override
    Optional<Member> findByName(String name);
}
