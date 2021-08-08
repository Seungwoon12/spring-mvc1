package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {

    // MemberRepository 인스턴스가 아무리 많아도 static 이기 때문에 딱 1개만 만들어지고 사용됨.
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store에 있는 값을 건드리고 싶지 않아서 새로운 list에 담아서 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // store에 있는 값을 다 날리는 것
    }

}
