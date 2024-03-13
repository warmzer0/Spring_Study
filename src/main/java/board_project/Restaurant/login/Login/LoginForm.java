package board_project.Restaurant.login.Login;

import board_project.Restaurant.login.annotation.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    
    @NotEmpty
    private String loginId;
    
    @NotEmpty
    private String password;

}
