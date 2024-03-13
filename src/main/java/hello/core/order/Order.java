package hello.core.order;
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    //order 객체를 생성하기 위한 생성자 부분
    //매개 변수의 값을 초기화
    public Order(Long memberId, String itemName, int itemPrice, int
            discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }


    //주문한 상품의 가격에서 할인된 가격을 계산해주는 메서드
    public int calculatePrice() {

        return itemPrice - discountPrice;
    }

    //getter와 setter라고 불리며, 객체의 필드 값을 가져오거나 설정하는 데 사용
    //getter -> 필드 값 반환
    //setter -> 해당 필드의 값을 설정
    public Long getMemberId() {

        return memberId;
    }

    public String getItemName() {

        return itemName;
    }

    public int getItemPrice() {

        return itemPrice;
    }

    public int getDiscountPrice() {

        return discountPrice;
    }

    //객체를 문자열로 표현하기 위한 메서드
    //주문 객체의 멤버 변수를 문자열로 표현하여 반환
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}