package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
//     ㄴ JPA는 엔티티 매니저로 모든걸 동작함
    // bulide.gradle에서 	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'을 통해
//     spring boot가 EntityManage을 만들어줌

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
//                                ㄴ JPQl이라는 쿼리언어 ( 테이블이 아니라 객체에 대해 쿼리를날림)
//                                      ㄴ Sql로 번역이 됨
    }
}
