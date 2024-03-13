package board_project.Restaurant.dashboard.service;

import board_project.Restaurant.dashboard.model.Restaurant;
import board_project.Restaurant.dashboard.model.Review;
import board_project.Restaurant.dashboard.model.dto.ReviewDto;
import board_project.Restaurant.dashboard.repository.RestaurantRepository;
import board_project.Restaurant.dashboard.repository.ReviewRepository;
import board_project.Restaurant.login.Member.Member;
import board_project.Restaurant.login.Member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, MemberRepository memberRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public List<Restaurant> listRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    @Transactional
    public Restaurant getRestaurantDetail(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @Override
    @Transactional
    public List<Restaurant> searchRestaurants(String restaurantName) {
        return restaurantRepository.findByRestaurantNameContaining(restaurantName);
    }

    @Override
    @Transactional
    public Review addReview(Long restaurantId, ReviewDto reviewDto) {

        Member member = memberRepository.findByLoginId(reviewDto.getMemberId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));


        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            Review review = new Review();
            review.setRestaurant(restaurant);
            review.setMember(member); // 여기에 사용자 정보를 설정합니다.
            review.setRating(reviewDto.getRating());
            review.setReviewText(reviewDto.getReviewText());

            // 리뷰 저장 전 로그
            log.info("Saving review for restaurantId={}, memberId={}, rating={}, text={}", restaurantId, member.getId(),reviewDto.getRating(), reviewDto.getReviewText());

            Review saved = reviewRepository.save(review);

            // 리뷰 저장 후 로그
            log.info("Review saved successfully for restaurantId={}", restaurantId);

            return saved;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    @Override
    @Transactional
    public List<Review> getReviewsByRestaurantId(Long restaurantId) {
        return reviewRepository.findByRestaurantRestaurantId(restaurantId);
    }

    @Override
    @Transactional
    public double calculateAverageRating(Long restaurantId) {
        List<Review> reviews = reviewRepository.findByRestaurantRestaurantId(restaurantId);
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    @Transactional
    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public class ReviewService {
        // 리뷰 저장 메서드
        public Review saveReview(Member member, Review review) {
            review.setMember(member); // 리뷰에 회원 설정
            return reviewRepository.save(review); // 리뷰 저장
        }
    }

    @Override
    public void deleteReview(Long reviewId, Long memberId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        if (!review.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("리뷰 삭제 권한이 없습니다.");
        }

        reviewRepository.deleteById(reviewId);
    }

}
