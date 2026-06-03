package role;

import policy.StaffSubmissionPolicy;
import policy.SubmissionPolicy;

/**
 * 운영진 - 직책(position)을 가지며, 과제 제출 대상이 아니다.
 */
public class Staff extends Role {
    private final String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public SubmissionPolicy getPolicy() {
        return new StaffSubmissionPolicy();
    }

    @Override
    public String getInfo() {
        return "👤 역할: 운영진\n" +
                "📌 이름: " + getName() +
                " | 🐾 전공: " + getMajor() +
                " | 🔢 기수: " + getGeneration() + "기" +
                " | 💻 파트: " + getPart() + "\n" +
                "💼 직책: " + position + "\n" +
                "📝 과제 제출 가능: " + (canSubmit() ? "✅ 가능" : "❌ 불가");
    }
}
