package board_project.Restaurant.dashboard.repository;

import board_project.Restaurant.dashboard.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 식당 이름 검색
    List<Restaurant> findByRestaurantNameContaining(String name);
}
