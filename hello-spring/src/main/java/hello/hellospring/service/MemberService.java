package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
 서비스에 해당하는 로직을 자바클래스를 통해 짜더라도 스플링입장에서는 자바코드로만 인식됨
 따라서 스프링이 시작되고 스프링컨테이너가 멤버서비스를 등록해야한다는 것을 알려주기 위해
 해당 클래스위에 서비스 어노테이션을 붙여줌
 */
//@Service
// 서비스는 비즈니에 의존적으로 네이밍함

@Transactional
// ㄴ jpa를 쓰기위해서는 항상 transactional이라는 어노테이션이 필요함
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {//레파지토리 객체를 새롯 생성해서 테스트 하는경우
                                                    // 새로운 Db를 만들어하는 느낌임으로 애매함
                                                    // 기존에 사용하던 레퍼지토리를 테스트에서도 이어서 사용하기위해
                                                    // 요렇게하
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     * */
    public Long join(Member member) {
        validateDuplicateMember(member);//중복회원검증
        memberRepository.save(member);
        return member.getId();

        //┌고전적인 시간호출 방법 -> 메소드관리가 힘듬 -> AOP로 해결
//        long start = System.currentTimeMillis();
//        try {
//            //동명이인 안됨 가정
//            validateDuplicateMember(member);//중복회원검증
//            memberRepository.save(member);
//            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish-start;
//            System.out.println("join = " +timeMs+"ms");
//        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                     throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
