package hello.core.member;

import java.util.HashMap;
import java.util.Map;

//interface와 구현체는 다른 패키지에 두는게 설계상 좋지만
//여기선 간단하게 같은 패키지
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //저장소

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
