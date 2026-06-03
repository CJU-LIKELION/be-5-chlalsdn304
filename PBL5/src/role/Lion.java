package role;

import policy.LionSubmissionPolicy;
import policy.SubmissionPolicy;

/**
 * 아기사자 - 학번(studentId)을 가지며, 과제 제출이 가능하다.
 */
public class Lion extends Role {
    private final int studentId;

    public Lion(String name, String major, int generation, String part, int studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public SubmissionPolicy getPolicy() {
        return new LionSubmissionPolicy();
    }

    @Override
    public String getInfo() {
        return "👤 역할: 아기사자\n" +
                "📌 이름: " + getName() +
                " | 🐾 전공: " + getMajor() +
                " | 🔢 기수: " + getGeneration() + "기" +
                " | 💻 파트: " + getPart() + "\n" +
                "🆔 학번: " + studentId + "\n" +
                "📝 과제 제출 가능: " + (canSubmit() ? "✅ 가능" : "❌ 불가");
    }
}
