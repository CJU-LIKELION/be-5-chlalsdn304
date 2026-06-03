package policy;

/** 아기사자 정책: 과제 제출 가능 */
public class LionSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return true;
    }
}
