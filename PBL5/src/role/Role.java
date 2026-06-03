package role;

import policy.SubmissionPolicy;

/**
 * 멤버(역할)의 공통 추상 클래스 - 3주차 공용 클래스.
 * 모든 멤버는 이름/전공/기수/파트를 가지며,
 * 역할별로 과제 제출 정책(SubmissionPolicy)이 다르다.
 */
public abstract class Role {
    private final String name;
    private final String major;
    private final int generation;
    private final String part;

    public Role(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    // Repository/Service가 다른 패키지에서 접근하므로 public 으로 공개
    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    /** 역할별 과제 제출 정책 (Lion / Staff 가 각각 구현) */
    public abstract SubmissionPolicy getPolicy();

    /** 화면에 출력할 상세 정보 */
    public abstract String getInfo();

    /** 정책에 위임하여 과제 제출 가능 여부를 판단 */
    public boolean canSubmit() {
        return getPolicy().canSubmit();
    }
}
