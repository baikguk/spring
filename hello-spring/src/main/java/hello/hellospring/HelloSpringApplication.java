package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Component를 사용해서 빈에 등록하는경우
//스프링이 hello.hellospring하위 패키지에서만 스캔해서 빈을 추출함
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
