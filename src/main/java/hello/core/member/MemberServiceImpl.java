package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service")
//MemberRepository 인터페이스를 구현하여 회원 정보를 메모리에 저장하고 조회하는 기능을 구현
public class MemberServiceImpl implements MemberService {


    //인터페이스를 구현한 구현체를 멤버 변수로 가진다. 이를 통해 실제로 회원 정보를 저장하고 조회하는 기능을 수행
    private final MemberRepository memberRepository;

    //자동으로 의존관계 주입 필요
    @Autowired //ac.getBean(MemberRepository.class)
    //생성자를 통해 memberRepository를 주입받습니다.
    // 이를 통해 의존성 주입(Dependency Injection)을 수행합니다.
    // 즉, 외부에서 MemberRepository 구현체를 주입받아 사용합니다.
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;

    }

    //memberRepository.save(member)를 호출하여 회원을 저장합니다.
    // memberRepository를 통해 실제 저장 기능을 수행
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }


    //memberRepository.findById(memberId)를 호출하여 해당 식별자에 해당하는 회원을 조회합니다.
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}


//객체 인스턴스의 다이어그램을 그리면
//app config가 메모리 멤버 레포지토리 객체를 생성
//멤버 서비스 객체를 만들때도
//메모리 멤버 리파지토리도 새성을 한다
// 멤버 서비스 인플을 새성을 할때 멤버 리파지토리인 참조간 x001을 같이 넘긴다.
//생성자에 멤버 서비스 인플은 생성한 메모리 멤버 리파지토리에 대한 값을 주입을 받게 된다.