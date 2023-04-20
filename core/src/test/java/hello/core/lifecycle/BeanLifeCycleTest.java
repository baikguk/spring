package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // ┌ 빈등록할 때 초기화 소멸 메소드도 등록
        @Bean/*(initMethod = "init",destroyMethod = "close")*/
        //                              ㄴ 디폴트는 destroyMethod = "(inferred)"
        //  destroyMethod = "(inferred)" -> 외부라이브러리에서 사용하는 메소드 이름을 추론함(close,shutdown)
        //  종료메소드는 위의 기능이 있기 때문에 따로 메소드를 안만들어도 종료 콜백해줌
        //  공백 문자시 추론을 하지 않음
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
