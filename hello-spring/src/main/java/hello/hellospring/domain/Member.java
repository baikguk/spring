package hello.hellospring.domain;
import javax.persistence.*;
//jpa는 Java persistence Api로 관계형 데이터베이를 관리함 , -> 일종의 인터페이스
// jpa는 orm(object relational mapping)이라는 기술임
//  hibernate는 jpa의 구현체

@Entity // jpa가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//PK를 설정해줘야함
//                              디비에 값을 넣을 때 pk를 자동생성해주는 것을 indentity 전략이라 함
    private Long id;

//    @Column(name = "username")
//              ㄴ 디비에 name이라는 컬럼을 username로 맵핑해줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
