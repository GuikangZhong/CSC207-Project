package project.interview;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class Interview implements Serializable {
    private static final long serialVersionUID = -1272861658449401440L;
    private final int maxInterview;
    private List<InterviewAssignment> assignments;
    private Set<String> nameApplicantPassed;
    private boolean assigned = false;

    Interview(int maximumInterview) {
        this.maxInterview = maximumInterview;
        nameApplicantPassed = null;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public Set<String> getNameApplicantPassed() {
        return nameApplicantPassed;
    }

    public void setNameApplicantPassed(Set<String> nameApplicantPassed) {
        this.nameApplicantPassed = nameApplicantPassed;
    }

    public int getMaxInterview() {
        return maxInterview;
    }

    void assign(List<InterviewAssignment> assignments) {
        this.assignments = assignments;
        assigned = true;
    }

    public abstract String getInterviewType();

    public List<InterviewAssignment> getAssignments() {
        return assignments;
    }
}
