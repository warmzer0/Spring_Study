package hello.core;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

//애플리케이션의 실행을 담당하는 역할
public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig(); //객체 생성
//        MemberService memberService = appConfig.memberService();

        //스프링은 모든게 애플리케이션 컨텍스트라는 걸로 시작함 = 스프링 컨테이너라고 생각하면 됨
        //이렇게 하게되면 app-config에 있는 환경 설정 정보를 가지고 스프링이 괄호안 AppConfig에 있는 @Bean이 객체 생성한 것을 다집어넣고 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    } }