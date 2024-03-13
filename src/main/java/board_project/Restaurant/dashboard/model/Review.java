package board_project.Restaurant.dashboard.model;

import board_project.Restaurant.login.Member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Review {

    @Id// 선택지 두개의 임포트 중 Jakarta로 임포트하면 JPA를 사용하여 ORM할 때 사용된다한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "reviewText")
    private String reviewText; // 리뷰 내용

    @Column(name = "rating")
    private int rating; // 별점 (1~5 정수로만)

    @ManyToOne //다대일 관계여야 하나의 가게에 여러개의 리뷰가 나올 수 있다.
    @JoinColumn(name = "restaurant_id") // 외래키
    private Restaurant restaurant;

    @ManyToOne // 리뷰와 회원은 다대일 관계
    @JoinColumn(name = "member_id") // 외래키
    private Member member;

    public Review() {

    }

}
