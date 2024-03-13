package board_project.Restaurant.dashboard.repository;

import board_project.Restaurant.dashboard.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRestaurantRestaurantId(Long restaurantId);
    // 특정 식당의 모든 리뷰를 찾는 메서드
}
