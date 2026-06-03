package policy;

/**
 * 과제 제출 정책 인터페이스 - 3주차 공용 클래스.
 * 역할마다 제출 가능 여부 규칙이 달라지므로 인터페이스로 추상화한다.
 */
public interface SubmissionPolicy {
    boolean canSubmit();
}
