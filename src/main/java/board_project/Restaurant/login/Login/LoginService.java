package board_project.Restaurant.login.Login;


import board_project.Restaurant.login.Member.Member;
import board_project.Restaurant.login.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    /**
     * @return null이면 로그인 실패 */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    public void withdraw(String loginId){
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            memberRepository.delete(member);
        }else{
            throw new IllegalArgumentException("헤당 회원을 찾을 수 없습니다.");
        }
    }

}
