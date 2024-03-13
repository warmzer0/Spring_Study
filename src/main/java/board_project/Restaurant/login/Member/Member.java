package board_project.Restaurant.login.Member;

import board_project.Restaurant.login.annotation.NotEmpty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Pattern(regexp = ".+@.*\\.com$", message = "로그인 ID는 .com으로 끝나야 합니다.")
    private String loginId; //로그인 ID @NotEmpty
    private String name; //사용자 이름 @NotEmpty
    private String password;
}
