package project.interview;

import java.util.List;

public abstract class Interview {
    public int getMaxInterview() {
        return maxInterview;
    }

    private final int maxInterview;
    private List<InterviewAssignment> assignments;

    Interview(int maximumInterview) {
        this.maxInterview = maximumInterview;
    }

    public abstract String getInterviewType();

    void setAssignments(List<InterviewAssignment> assignments) {
        this.assignments = assignments;
    }
}
