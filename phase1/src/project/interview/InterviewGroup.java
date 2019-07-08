package project.interview;

import project.application.Company;
import project.user.Applicant;
import project.user.Interviewer;

import java.io.Serializable;
import java.util.*;

public class InterviewGroup implements Serializable {
    private static final long serialVersionUID = 4355176965416536403L;
    private Interviewer interviewer;
    private Map<String, Boolean> applicantsStatus;
    private List<Applicant> applicants;
    private Round round;
    private boolean submitted = false;
    private Interview interview;

    InterviewGroup(Interview interview, Interviewer interviewer, List<Applicant> applicants) {
        this.interviewer = interviewer;
        this.applicants = applicants;
        applicantsStatus = new HashMap<>();
        for (Applicant applicant : applicants) {
            applicantsStatus.put(applicant.getUsername(), false);
        }
        this.interview = interview;
    }

    public void submit() {
        submitted = true;
        round.updateOnGroupSubmitted();
        interviewer.removeInterviewGroup(this);
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public List<Applicant> getApplicantsPassed() {
        List<Applicant> result = new ArrayList<>();
        for (Applicant applicant : applicants) {
            if (applicantsStatus.get(applicant.getUsername())) {
                result.add(applicant);
            }
        }
        return result;
    }

    public void setApplicantPassed(String name, boolean passed) {
        applicantsStatus.put(name, passed);
    }
}
