package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
//주문서비스를 구현한 클래스
public class OrderServiceImpl implements OrderService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); -> dip위반 코드가 된다


    //생성자에서 주입 받음
    //구체 클래스에 의존하는 것이 아니라 인터페이스에 의존하도록 설계함
    //dip(dependency inversion principle)를 지킴
    //구체 클래스에 의존하면 해당 클래스가 변경되면 의존하는 클래스의 코드도 변경해야 합니다.
    // 하지만 인터페이스에 의존하면 인터페이스를 구현하는 다른 클래스로 쉽게 교체할 수 있습니다.
    private final DiscountPolicy discountPolicy; //구체에 의존하지 않고 추상화인 인터페이스에 의존한다.(*얘가 넘어가서)
    private final MemberRepository memberRepository; //private final은 무조건 값이 있어야한다는 뜻

//    @Autowired
    //결국 생성자가 하나면 오토와이어를 생략해도 같은 결과를 도출함
//    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
//        //이 생성자를 통해서 메모리 멤버 리파지토리가 넘어가고 디스카운트 폴리시도 픽스
//        this.discountPolicy = discountPolicy;//(*여기에 할당)
//        this.memberRepository = memberRepository;
//    }



    @Autowired
    public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        //이 생성자를 통해서 메모리 멤버 리파지토리가 넘어가고 디스카운트 폴리시도 픽스
        this.discountPolicy = discountPolicy;//(*여기에 할당)
        this.memberRepository = memberRepository;
    }

    //memberId를 통해 회원 정보를 조회하고, discountPolicy를 통해 할인된 가격을 계산하여 Order 객체를 생성합니다.
    // 이를 통해 주문을 생성하는 기능을 구현
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
