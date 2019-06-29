package project.interview;

import java.util.List;
import java.util.Set;

public abstract class Interview {
    private final int maxInterview;
    private List<InterviewAssignment> assignments;

    public Set<String> getNameApplicantPassed() {
        return nameApplicantPassed;
    }

    public void setNameApplicantPassed(Set<String> nameApplicantPassed) {
        this.nameApplicantPassed = nameApplicantPassed;
    }

    private Set<String> nameApplicantPassed;

    Interview(int maximumInterview) {
        this.maxInterview = maximumInterview;
        nameApplicantPassed = null;
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
