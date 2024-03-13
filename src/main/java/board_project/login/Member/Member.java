package board_project.login.Member;

import board_project.login.annotation.NotEmpty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.Pattern;


@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Pattern(regexp = ".+@sqisoft\\.com$", message = "로그인 ID는 @sqisoft.com으로 끝나야 합니다.")
    private String loginId; //로그인 ID @NotEmpty
    private String name; //사용자 이름 @NotEmpty
    private String password;
}
