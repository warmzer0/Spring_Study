package hello.core.member;

// MemberRepository 인터페이스
// 이 인터페이스는 회원 정보를 저장하고 조회하기 위한 메서드를 정의
// 회원 정보를 저장하고 조회하는 기능을 추상화하여 정의
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}