package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct; //javax 는 자바에소 제공하는 표준 어노테이션임
import javax.annotation.PreDestroy; // 스프링외의 컨테이너에서도 사용 가능함

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자를 호출, url = " + url);
//        connect(); 스프링의 라이프사이클은
//        call("초기화 연결 메세지");
//          ㄴ 의존관계 주입전에 호출되어 url과 message에 null이 들어감

        // 컨테이너 생성-> 스프링 빈생성-> 의존관계주입->초기화 콜백 ->
        //                                      사용ㅇ -> 소멸전 콜백 ->스프링 종료이다
        //                      ㄴ생성자 주입의 경우 빈 생성시 의존관계도 주입됨
        // 값을 초기화 하기 위해서는 스프링 라이프사이클에 따라 의존관계주입이 끝난 후 진행햐야함
        // 프로그래머 입장에서 의존관계주입이ㅣ 끝난시점을 알기 위해 여러 방법을 스프링에서 제공함
        // 방법1: implements InitializingBean, DisposableBean 인터페이스 상속하여 초기화 콜백 소멸전 콜백 오버라이드
        //                                 ㄴ 단점 : 스프링 전용이라 스프링에 의존적으로 코드를 작성해야함, 메소드 이름을 바꾸지못해 호환성이 낮늠
        //                                  ㄴ 따라서 잘 사용안함
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    // 연결된 서버에서 메시지를 부름
    public void call(String message) {
        System.out.println("call = " + url+ "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close"+url);
    }

    @PostConstruct // -> 코드를 고칠 수 없는외부 라이브러리 에서는 사용하지 못함
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}
