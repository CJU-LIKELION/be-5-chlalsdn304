package package1;

import role.Role;

import java.util.List;

/**
 * [Step 1] 비즈니스 로직 담당 Service.
 *
 * ★ 강한 결합(Tight Coupling)의 예 ★
 * Service 가 내부에서 Repository 를 직접 new 로 생성한다.
 *  - 저장소를 다른 구현으로 바꾸려면 이 클래스의 코드를 직접 고쳐야 한다.
 *  - 테스트할 때 가짜 저장소로 갈아끼울 수 없다.
 */
public class MemberService {
    // ❗ Repository 를 직접 생성 → MemberRepository(구체 클래스)에 강하게 묶임
    private final MemberRepository repository = new MemberRepository();

    /** 중복 확인 후 등록 (성공 true / 중복이면 false) */
    public boolean register(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    /** 이름 중복 여부 */
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    /** 이름으로 검색 */
    public Role search(String name) {
        return repository.findByName(name);
    }

    /** 전체 멤버 조회 */
    public List<Role> findAll() {
        return repository.findAll();
    }
}
