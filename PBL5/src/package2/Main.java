package package2;

import role.Lion;
import role.Role;
import role.Staff;

import java.util.List;
import java.util.Scanner;

/**
 * [Step 2] 의존성 주입(DI) 버전 실행 클래스.
 *
 * 여기가 "조립(연결)을 담당하는 곳"이다.
 *  1) Repository 구현체를 선택해서 생성한다. (Memory / Mock)
 *  2) 생성한 Repository 를 Service 생성자에 주입한다.
 *  → 구현체만 바꾸면 Service/Repository 코드 수정 없이 동작이 달라진다. (제어의 역전, IoC)
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 1) 저장소(구현체) 선택
        System.out.println("🗄 저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");
        String choice = sc.nextLine().trim();

        MemberRepository repository; // ← 인터페이스 타입
        if (choice.equals("2")) {
            repository = new MockMemberRepository();
        } else {
            repository = new MemoryMemberRepository();
        }

        // 2) 의존성 주입: 선택한 Repository 를 Service 에 넣어준다
        MemberService service = new MemberService(repository);
        System.out.println("➡️  주입된 저장소: " + repository.getClass().getSimpleName() + "\n");

        // 3) 메뉴 실행
        while (true) {
            printMenu();
            String input = sc.nextLine().trim();
            switch (input) {
                case "1" -> register(service);
                case "2" -> showAll(service);
                case "3" -> search(service);
                case "4" -> {
                    System.out.println("👋 프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("❌ 올바른 번호를 입력하세요.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("🦁 ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) =====");
        System.out.println("1. ➕ 멤버 등록");
        System.out.println("2. 📋 전체 멤버 조회");
        System.out.println("3. 🔍 이름으로 검색");
        System.out.println("4. 🚪 종료");
        System.out.print("선택: ");
    }

    private static void register(MemberService service) {
        System.out.print("\n👤 역할 선택 (1: 아기사자, 2: 운영진): ");
        String role = sc.nextLine().trim();
        if (!role.equals("1") && !role.equals("2")) {
            System.out.println("❌ 역할은 1 또는 2만 입력 가능합니다.\n");
            return;
        }

        System.out.println("\n📝 정보 입력");
        System.out.print("이름: ");
        String name = sc.nextLine().trim();
        if (service.existsByName(name)) {
            System.out.println("❌ 이미 등록된 이름입니다: " + name + "\n");
            return;
        }

        System.out.print("전공: ");
        String major = sc.nextLine().trim();

        System.out.print("기수: ");
        int generation;
        try {
            generation = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ 기수는 숫자여야 합니다.\n");
            return;
        }

        System.out.print("파트: ");
        String part = sc.nextLine().trim();

        Role member;
        if (role.equals("1")) {
            System.out.print("학번: ");
            int studentId;
            try {
                studentId = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ 학번은 숫자여야 합니다.\n");
                return;
            }
            member = new Lion(name, major, generation, part, studentId);
        } else {
            System.out.print("직책: ");
            String position = sc.nextLine().trim();
            member = new Staff(name, major, generation, part, position);
        }

        if (service.register(member)) {
            System.out.println("\n✅ 등록 완료: " + name + "\n");
        } else {
            System.out.println("\n❌ 등록 실패 (중복): " + name + "\n");
        }
    }

    private static void showAll(MemberService service) {
        System.out.println("\n📋 ===== 전체 멤버 조회 =====");
        List<Role> all = service.findAll();
        if (all.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.\n");
            return;
        }
        for (Role m : all) {
            System.out.println(m.getInfo());
            System.out.println("--------------------------------");
        }
        System.out.println();
    }

    private static void search(MemberService service) {
        System.out.print("\n🔍 검색할 이름: ");
        String name = sc.nextLine().trim();
        Role m = service.search(name);
        System.out.println("\n🦁 ===== 검색 결과 =====");
        if (m == null) {
            System.out.println("❌ 해당 이름의 멤버를 찾을 수 없습니다: " + name + "\n");
        } else {
            System.out.println(m.getInfo());
            System.out.println();
        }
    }
}
