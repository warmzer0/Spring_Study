package hello.core.order;

//인터페이스 정의
public interface OrderService {

    //주문을 생성하는 기능을 수행
    //Order 객체를 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
