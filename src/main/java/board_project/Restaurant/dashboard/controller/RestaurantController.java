package board_project.Restaurant.dashboard.controller;

import board_project.Restaurant.dashboard.model.Restaurant;
import board_project.Restaurant.dashboard.model.Review;
import board_project.Restaurant.dashboard.model.dto.ReviewDto;
import board_project.Restaurant.dashboard.service.RestaurantService;
import board_project.Restaurant.login.Member.Member;
import board_project.Restaurant.login.Member.MemberService;
import board_project.Restaurant.login.Session.SessionConst;
import board_project.Restaurant.login.Session.SessionManager;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("basic/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final SessionManager sessionManager;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, SessionManager sessionManager) {
        this.restaurantService = restaurantService;
        this.sessionManager = sessionManager;

    }

    // 모든 식당을 조회함
    @GetMapping
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.listRestaurants();

        model.addAttribute("restaurants", restaurants);

        // 식당 목록 페이지로 이동
        return "basic/list";
    }

    // 식당 상세 정보 조회
    @GetMapping("/{restaurantId}")
    @Transactional
    public String showRestaurantDetail(@PathVariable(name = "restaurantId") Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantDetail(restaurantId);
        List<Review> reviews = restaurantService.getReviewsByRestaurantId(restaurantId);
        // 평균 별점 계산
        double averageRating = restaurantService.calculateAverageRating(restaurantId);
        // 평균 별점을 소수점 한 자리까지 포맷팅
        String formattedAverageRating = String.format("%.1f", averageRating);

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageRating", formattedAverageRating);

        // 가게 상세 정보 페이지의 뷰 이름
        return "basic/detail";
    }

    // 식당 이름으로 검색
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam("restaurantName") String restaurantName, Model model) {
        // 주어진 문자열로 가게 이름 검색
        List<Restaurant> restaurants = restaurantService.searchRestaurants(restaurantName);

        model.addAttribute("restaurants", restaurants);

        return ResponseEntity.ok(restaurants);
    }

    // 리뷰 추가 폼 페이지로 이동
    @GetMapping("/{restaurantId}/addReview")
    public String addReviewForm(@PathVariable Long restaurantId, Model model) {
        // 해당 식당의 상세 정보를 가져와 모델에 추가할 수도 있습니다.
        Restaurant restaurant = restaurantService.getRestaurantDetail(restaurantId);

        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("restaurant", restaurant);

        // 리뷰 추가 폼 페이지의 뷰 이름을 반환
        return "basic/addReview";
    }

    // 리뷰 추가 처리
    @PostMapping("/{restaurantId}/reviews")
    @ResponseBody
    public ResponseEntity<?> addReview(@PathVariable Long restaurantId, @RequestBody ReviewDto reviewDto,
                                       HttpServletRequest request) {
        // 사용자 loginId 조회
        Member loginMember = (Member) sessionManager.getSession(request);
        // 로그인한 사용자가 없는 경우 에러 응답을 반환합니다.
        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        String memberLoginId = loginMember.getLoginId();
        reviewDto.setMemberId(memberLoginId);
        // 요청 로그 출력
        log.info("Received review: {}", reviewDto);

        try {
            restaurantService.addReview(restaurantId, reviewDto);

            Map<String, String> response = new HashMap<>();
            response.put("message", "리뷰가 성공적으로 추가되었습니다.");
            response.put("redirectUrl", "/basic/restaurants/" + restaurantId);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("errorMessage", "리뷰 추가에 실패했습니다.");

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    //리뷰 삭제 :  삭제하려는 리뷰의 ID와 로그인한 사용자의 ID를 확인.
    @PostMapping("/{restaurantId}/reviews/{reviewId}/delete")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        try {
            restaurantService.deleteReview(reviewId, loginMember.getId());
            return ResponseEntity.ok().body("리뷰가 성공적으로 삭제되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("리뷰 삭제에 실패했습니다.");
        }
    }

    //예시 데이터 생성
    @PostConstruct
    public void init() {
        Restaurant restaurant1 = new Restaurant(null, "버거킹 영등포KT점", "서울특별시 영등포구 영등포동8가 35-1",
                "https://d12zq4w4guyljn.cloudfront.net/750_750_20231216033743882_photo_7711b4aca86d.jpg");
        Restaurant restaurant2 = new Restaurant(null, "문래동 돈까스", "서울특별시 영등포구 문래동2가 56-1",
                "https://d12zq4w4guyljn.cloudfront.net/750_750_20230701111601_photo1_b4594ca34a11.jpg");

        restaurantService.addRestaurant(restaurant1);
        restaurantService.addRestaurant(restaurant2);
    }
}
