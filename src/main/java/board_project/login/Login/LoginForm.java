package board_project.login.Login;

import board_project.login.annotation.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    
    @NotEmpty
    private String loginId;
    
    @NotEmpty
    private String password;

}
