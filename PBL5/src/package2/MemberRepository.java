package package2;

import role.Role;

import java.util.List;

/**
 * [Step 2] 멤버 저장소 - "인터페이스".
 *
 * 저장소가 "무엇을 할 수 있는지"(약속)만 선언한다.
 * "어떻게 저장하는지"(구현)는 MemoryMemberRepository / MockMemberRepository 가 담당.
 * Service 는 이 인터페이스에만 의존하므로 구현체를 자유롭게 갈아끼울 수 있다.
 */
public interface MemberRepository {
    void save(Role member);

    boolean existsByName(String name);

    Role findByName(String name);

    List<Role> findAll();
}
