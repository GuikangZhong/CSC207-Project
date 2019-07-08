package project.interview;

import project.application.Company;
import project.observer.InterviewGroupObserver;
import project.user.Applicant;
import project.user.Interviewer;
import project.utils.Logging;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

public class InterviewGroup implements Serializable {
    private static final long serialVersionUID = 4355176965416536403L;
    private Interviewer interviewer;
    private Map<String, Boolean> applicantsStatus;
    private List<Applicant> applicants;
    private boolean submitted = false;
    private Interview interview;
    private List<InterviewGroupObserver> observers;
    static private Logger logger = Logging.getLogger();

    public void addObserver(InterviewGroupObserver observer) {
        logger.info(this + "add observer " + observer);
        observers.add(observer);
    }

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
        for (InterviewGroupObserver observer : observers) {
            observer.updateOnGroupSubmitted(this);
        }
//
//                interviewer.removeInterviewGroup(this);
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
