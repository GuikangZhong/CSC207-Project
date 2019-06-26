package project.interview;

import java.util.List;

public class InPersonInterview extends Interview{
    private final int maxInterview;
    private List<InterviewAssignment> assignments;

    InPersonInterview(int maximumInterview) {
        super(maximumInterview);
        this.maxInterview = maximumInterview - 1;
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
