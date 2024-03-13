package hello.core.member;

//회원 가입과 회원 조회를 위한 메서드를 정의를 위한 추상화형태 정의
public interface MemberService {

    //반환값이 필요 없으므로 void형태
    void join(Member member);

    //반환값이 필요하므로 Member형태(Member 클래스)
    Member findMember(Long memberId);
}