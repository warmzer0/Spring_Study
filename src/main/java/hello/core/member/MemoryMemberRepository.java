package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component//MemoryMemberRepository
//MemberRepository 인터페이스를 구현하여 회원 정보를 메모리에 저장하고 조회하는 기능을 구현
public class MemoryMemberRepository implements MemberRepository {

    //Map은 키(Key)와 값(Value)을 매핑하는 자료구조를 나타내며,
    // 여기서는 Long 타입의 키와 Member 타입의 값으로 매핑되는 Map 객체를 의미합니다.
    // Long은 회원 식별자를 나타내고, Member는 회원 객체를 나타낸다
    private static Map<Long, Member> store = new HashMap<>();
    //member 객체를 store에 저장합니다.


    //회원정보 저장 메소드
    // member.getId()를 키로 사용하여 회원을 식별하고, member 객체를 값으로 저장
    @Override
    public void save(Member member) {

        store.put(member.getId(), member);
    }

    //회원 식별자를 기반으로 회원 정보를 조회하는 메서드입니다.
    // memberId를 키로 사용하여 store에서 해당 회원을 찾아 반환합니다.
    @Override
    public Member findById(Long memberId) {

        return store.get(memberId);
    }
}