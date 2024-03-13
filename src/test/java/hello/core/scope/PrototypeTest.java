package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//프로토타입 빈 스코프 테스트
public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        //컴피그에서 컴포넌트 스캔을 읽은 것임
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //클로즈가 전혀 안된다.
        //이름 그대로 프로토타입이라 만들고 버린 것임 !
        ac.close();
    }

    //여기엔 사실 컴포넌트가 없는데 AnnotationConfig에 이렇게 지정해주면 이 클래스가 ConformanceScan의 대상자체처럼
    //동작하기 때문에 그냥 얘를 바로 스프링 빈으로 등록해버린다.
    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
