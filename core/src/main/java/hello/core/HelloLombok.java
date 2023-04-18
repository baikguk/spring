package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombok {// 롬복기능 확인을 위한 클래스

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asd");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        // 어노테이션을 추가한 내용을 자동으로 만들어줌
    }
}
