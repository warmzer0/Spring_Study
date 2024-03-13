package board_project.login.Member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Slf4j
//@Repository
//public class MemberRepositoryImpl implements MemberRepository {
//    private static Map<Long, Member> store = new HashMap<>(); //static 사용
//    private static long sequence = 0L; //static 사용
//
//    @Override
//    public Member save(Member member) {
//        member.setId(++sequence);
//        log.info("save: member={}", member);
//        store.put(member.getId(), member);
//        return member;
//    }
//
////    @Override
////    public Member findById(Long id) {
////        return store.get(id);
////    }
//
//    @Override
//    public Optional<Member> findByLoginId(String loginId) {
//        return findAll().stream()
//                .filter(m -> m.getLoginId().equals(loginId))
//                .findFirst();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public void clearStore() {
//        store.clear();
//    }
//
//    @Override
//    public void delete(Member member) {
//        if (member != null) {
//            store.remove(member.getId()); //회원이 존재하는 경우에만 삭제 -> 회원 삭제
//            log.info("deleteByLoginId: member={} deleted", store.get(member));
//            log.info("deleteByLoginId: member={} deleted", member);
//        } else {
//            log.info("deleteByLoginId: member not found for id={}", store.get(member));
//            log.info("삭제할 회원이 존재하지 않습니다.");
//        }
//    }
//}
//
