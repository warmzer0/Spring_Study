package board_project.Restaurant.dashboard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Restaurant {
    @Id // 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY)// 기본 키의 값을 자동으로 생성, IDENTITY는 데이터베이스에 생성을 위임함
    private Long restaurantId;

    @Column(name = "restaurantName")
    private String restaurantName;

    @Column(name = "location")
    private String location; // 가게 주소

    // 사진 URL을 위한 필드 추가
    private String photoUrl;


    public Restaurant() {
    }

    public Restaurant(Long restaurantId, String restaurantName, String location, String photoUrl) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.location = location;
        this.photoUrl =  photoUrl;
    }
}
