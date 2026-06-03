package package2;

import role.Lion;
import role.Role;
import role.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * [Step 2] Mock(더미) 구현체.
 * 미리 정의된 더미 데이터를 반환한다. 실제 저장은 하지 않는다.
 * 같은 인터페이스를 구현하므로 Service 코드 수정 없이 Memory 구현체와 교체할 수 있다.
 */
public class MockMemberRepository implements MemberRepository {
    private final List<Role> dummy = new ArrayList<>();

    public MockMemberRepository() {
        dummy.add(new Lion("더미사자", "컴퓨터공학과", 14, "백엔드", 20240001));
        dummy.add(new Staff("더미운영", "경영학과", 13, "기획", "회장"));
    }

    @Override
    public void save(Role member) {
        // Mock 저장소는 실제로 저장하지 않는다.
        System.out.println("⚠️ (Mock 저장소) 실제로 저장하지 않습니다. 더미 데이터만 제공합니다.");
    }

    @Override
    public boolean existsByName(String name) {
        for (Role m : dummy) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Role findByName(String name) {
        for (Role m : dummy) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        return dummy;
    }
}
