package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 서비스는 비즈니에 의존적으로 네이밍함
public class    MemberService {

    private final MemberRepository memberRepository;

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
        //동명이인 안됨 가정
        validateDuplicateMember(member);//중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findbyName(member.getName())
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
        return memberRepository.findbyId(memberId);
    }
}
