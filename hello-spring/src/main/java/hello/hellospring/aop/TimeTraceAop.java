package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
// 메소드 호출의 시간측정같은 경우 핵심관심사항이아니고 공통된 관심사항이다
// 따라서 원하는곳에 공통 관심사항 만들어 적용한다

@Component
@Aspect //AOP 지정 annotation
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
                //ㄴ AOP적용 범위
    public Object execute(ProceedingJoinPoint joinPoint ) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START : "+ joinPoint.toString());
        try{
            // 다음 메소드로 진행
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END : "+ joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
