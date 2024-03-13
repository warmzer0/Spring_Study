package board_project.Restaurant.dashboard.service;

import board_project.Restaurant.dashboard.model.Restaurant;
import board_project.Restaurant.dashboard.model.Review;
import board_project.Restaurant.dashboard.model.dto.ReviewDto;
import board_project.Restaurant.login.Member.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RestaurantService {
    List<Restaurant> listRestaurants();

    Restaurant getRestaurantDetail(Long restaurantId);

    List<Restaurant> searchRestaurants(String restaurantName);

    double calculateAverageRating(Long restaurantId);

    Review addReview(Long restaurantId, ReviewDto reviewDto);

    List<Review> getReviewsByRestaurantId(Long restaurantId);

    void addRestaurant(Restaurant restaurant);

    void deleteReview(Long reviewId, Long memberId) throws EntityNotFoundException;;
}
