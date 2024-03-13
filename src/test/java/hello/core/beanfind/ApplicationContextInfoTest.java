package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

//@bean등록이 잘 되었는지 조회하는 테스트 클래스
public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){

        //정리된 bin을 모두 등록
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            //application context에서 bean을 꺼낸다.
            Object bean = ac.getBean(beanDefinitionName); //object가 꺼내진다.

            //container에 있는 모든 빈을 가져옴
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){

        //정리된 bin을 모두 등록
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            //bin에대한 어떤 정보들(메타데이터), 빈 하나하나에 대한 빈 데피니션 네임을 넣으면 이걸 꺼낼 수 있음
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){

                //application context에서 bean을 꺼낸다.
                Object bean = ac.getBean(beanDefinitionName); //object가 꺼내진다.

                //container에 있는 모든 빈을 가져옴
                System.out.println("name = " + beanDefinitionName + " object = " + bean);

            }
        }
    }


}
