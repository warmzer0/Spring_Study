package board_project.Restaurant.login.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member save(Member member);
    //Member findById(Long id);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findAll();
    //void clearStore();
    // 회원 이름으로 찾는 기능 추가
    Optional<Member> findByName(String name);
    void delete(Member member);
}
