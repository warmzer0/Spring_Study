package hello.core;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//OrderApp 클래스는 AppConfig를 사용하여 MemberService와 OrderService를 생성하고,
// 회원 등록과 주문 생성을 수행하는 간단한 애플리케이션을 실행합니다.
public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//
//        //appconfig의 각각의 메서드를 호출하여 객체를 생성한다.
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        long memberId = 1L; //변수에 값 할당
        Member member = new Member(memberId, "memberA", Grade.VIP); //Memeber의 member객체를 생성하고 값 초기화
        memberService.join(member); //멤버 서비스의 join을 호출하여 member를 회원으로 등록

        //orderService의 createOrder() 메서드를 호출하여 memberId, "itemA", 10000 값을 인자로 전달하여 주문을 생성합니다.
        //생성된 주문은 order 변수에 저장
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
    }
}
