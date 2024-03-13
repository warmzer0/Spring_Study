package board_project.Restaurant.dashboard.model.dto;

import board_project.Restaurant.login.Member.MemberDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Getter
@Setter
public class ReviewDto {
    @NotNull
    private int rating;

    @NotNull
    private String reviewText;

    @NotNull
    private String memberId;

    // 작성자 정보
    private MemberDto memberDto;
}
