package policy;

/** 운영진 정책: 과제 제출 대상 아님 */
public class StaffSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}
