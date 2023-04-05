/*
* 테스트 주도 개발(TDD) - 테스트를 먼저만들고 이후에 구현개발
* */

package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositorytTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 콜백메소드로 각 메소드가 끝날때마다 호출됨
    // 각각의 메소드만 실행하면 오류가 없지만
    // test 전체를 실행하게 되면 임의로 실행되는 메소드의 순서때문에
    //  다른 메소드 테스트할때 영향을 미칠수 있엄
    // 테스트 하나가 끝나면 데이터를 클리어 해야함
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //맨뒤의  .get()은 Optinal을 벗긴값을 얻음
//        Assertions.assertEquals(member,result); // == System.out.println(result==memeber)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6을통해 밑에 변수도 같이 봐꿔줌
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();//자동변수 단축키 ctrl+alt+v
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
