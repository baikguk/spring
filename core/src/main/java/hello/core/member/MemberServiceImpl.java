package hello.core.member;

public class MemberServiceImpl implements MemberService {
    //구현체가 하나일때 변수뒤에 Impl을 붙임

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return  memberRepository.findById(memberId);
    }
}
