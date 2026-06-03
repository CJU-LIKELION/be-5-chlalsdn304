package package2;

import role.Role;

import java.util.List;

/**
 * [Step 2] 비즈니스 로직 담당 Service.
 *
 * ★ 느슨한 결합(Loose Coupling) + 의존성 주입(DI) ★
 *  - 구체 클래스가 아니라 MemberRepository "인터페이스"에만 의존한다.
 *  - Repository 를 직접 생성하지 않고, 생성자를 통해 외부에서 주입받는다.
 *  - repository 필드는 final → 한 번 주입되면 바뀌지 않는다.
 *
 * 덕분에 어떤 구현체(Memory/Mock)가 들어오든 이 코드는 전혀 바뀌지 않는다.
 */
public class MemberService {
    private final MemberRepository repository; // 인터페이스 타입 + final

    // 생성자 주입(Constructor Injection)
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

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
