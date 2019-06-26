package project.interview;

import java.util.List;

public class PhoneInterview extends Interview{
    private final int maxInterview;
    private List<InterviewAssignment> assignments;

    PhoneInterview(int maximumInterview) {
        super(maximumInterview);
        this.maxInterview = 0;
    }

    public int getMaxInterview() {
        return maxInterview;
    }

    void setAssignments(List<InterviewAssignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String getInterviewType() {
        return "Phone Interview";
    }
}
