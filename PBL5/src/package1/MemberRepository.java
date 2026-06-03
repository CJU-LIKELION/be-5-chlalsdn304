package package1;

import role.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * [Step 1] 멤버 저장소 - "구체 클래스".
 * 메모리(List)에 직접 저장한다.
 * Step 1에서는 인터페이스 없이 이 구현 클래스를 그대로 사용한다.
 */
public class MemberRepository {
    private final List<Role> members = new ArrayList<>();

    /** 멤버 저장 */
    public void save(Role member) {
        members.add(member);
    }

    /** 이름 중복 확인 */
    public boolean existsByName(String name) {
        for (Role m : members) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /** 이름으로 검색 (없으면 null) */
    public Role findByName(String name) {
        for (Role m : members) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    /** 전체 조회 */
    public List<Role> findAll() {
        return members;
    }
}
