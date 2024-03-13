package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//설정 정보니까 Configuration해준다.
@Configuration
//스프링 빈을 쫙 긁어 자동으로 스프링 빈으로 끌어 올려줌
@ComponentScan(

        //basepackage로 탐색할 패키지의 시작 위치를 지정한다.이 패키지를 포함하여 하위 패키지를 모두 탐색한다.
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        //exclude해서 골뱅이 필터를 하면 스캔으로 여기저기 다 뒤져서 스프링 빈으로 자동 등록하는데 그주에 다 뺄거를 다 지정해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
