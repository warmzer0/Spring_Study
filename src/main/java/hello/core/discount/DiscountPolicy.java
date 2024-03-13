package hello.core.discount;
import hello.core.member.Member;
public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */

    //discount() 메서드는 Member 객체와 가격(price)을 받아서 할인 대상 금액을 반환하는 추상 메서드
    int discount(Member member, int price);
}
