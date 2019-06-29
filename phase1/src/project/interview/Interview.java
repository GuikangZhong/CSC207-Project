package project.interview;

import java.util.List;

public abstract class Interview {
    private final int maxInterview;
    private List<InterviewAssignment> assignments;

    Interview(int maximumInterview) {
        this.maxInterview = maximumInterview;
    }

    public int getMaxInterview() {
        return maxInterview;
    }

    void setAssignments(List<InterviewAssignment> assignments) {
        this.assignments = assignments;
    }

    public abstract String getInterviewType();

    public List<InterviewAssignment> getAssignments() {
        return assignments;
    }
}
