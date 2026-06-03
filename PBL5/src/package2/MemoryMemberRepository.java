package package2;

import role.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * [Step 2] 메모리 기반 구현체.
 * 실제로 List 에 데이터를 저장하고 조회한다.
 */
public class MemoryMemberRepository implements MemberRepository {
    private final List<Role> members = new ArrayList<>();

    @Override
    public void save(Role member) {
        members.add(member);
    }

    @Override
    public boolean existsByName(String name) {
        for (Role m : members) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Role findByName(String name) {
        for (Role m : members) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return members;
    }
}
