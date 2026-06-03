package package1;

import role.Lion;
import role.Role;
import role.Staff;

import java.util.List;
import java.util.Scanner;

/**
 * [Step 1] 강한 결합 버전 실행 클래스.
 * Main 은 Service 만 사용한다. (Repository 를 직접 다루지 않는다)
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final MemberService service = new MemberService();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String input = sc.nextLine().trim();
            switch (input) {
                case "1" -> register();
                case "2" -> showAll();
                case "3" -> search();
                case "4" -> {
                    System.out.println("👋 프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("❌ 올바른 번호를 입력하세요.\n");
            }
        }
    }

    private static void printMenu() {
        System.out.println("🦁 ===== 멋사 멤버 관리 시스템 (Step 1: 강한 결합) =====");
        System.out.println("1. ➕ 멤버 등록");
        System.out.println("2. 📋 전체 멤버 조회");
        System.out.println("3. 🔍 이름으로 검색");
        System.out.println("4. 🚪 종료");
        System.out.print("선택: ");
    }

    private static void register() {
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

    private static void showAll() {
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

    private static void search() {
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
