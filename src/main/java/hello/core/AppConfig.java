package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


//AppConfig 다른 객체를 생성하고 의존성을 관리하는 역할
//애플리케이션의 구성을 책임
//@Configuration은 설정 정보
@Configuration
public class AppConfig {

    //MemberS
    // ervice 인터페이스를 구현한 MemberServiceImpl 객체를 반환합니다.
    // 이 때, MemberServiceImpl 생성자에는 MemoryMemberRepository 객체가 주입됩니다.
    // 따라서 memberService()를 호출하면 MemberServiceImpl 객체가 생성되며, 해당 객체는 MemoryMemberRepository를 의존하고 있습니다.
    @Bean
    public MemberService memberService() {

        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //새로 만들어진 객체는 MemoryMemberRepository()한테 의존
    }

    //@Beanㅇ르 붙이면 스프링 컨테이너라는 곳에 등록이 된다.
    @Bean
    @Primary
    public MemberRepository memberRepository(){

        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {

        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
