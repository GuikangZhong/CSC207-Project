package project.interview;

import java.io.Serializable;
import java.util.*;

public class InterviewGroup implements Serializable {
    private static final long serialVersionUID = 4355176965416536403L;
    private String interviewer;
    private Map<String, Boolean> applicantsStatus;
    private Round round;
    private boolean submitted = false;

    InterviewGroup(Interview interview, String interviewer, List<String> applicants) {
        this.interviewer = interviewer;
        applicantsStatus = new HashMap<>();
        for (String applicant : applicants) {
            applicantsStatus.put(applicant, false);
        }
    }

    public void submit() {
        submitted = true;
        round.updateOnGroupSubmitted();
    }

    public String getInterviewer() {
        return interviewer;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public List<String> getApplicantsNamePassed() {
        List<String> result = new ArrayList<>();
        for (String name : applicantsStatus.keySet()) {
            if (applicantsStatus.get(name)) {
                result.add(name);
            }
        }
        return result;
    }

    public void setApplicantPassed(String name, boolean passed){
        applicantsStatus.put(name, passed);
    }
}
