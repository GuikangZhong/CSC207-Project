package project.interview;

import project.application.Application;
import project.application.Company;
import project.application.Job;
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

    public List<Applicant> getApplicants() {
        return Collections.unmodifiableList(applicants);
    }

    private List<Applicant> applicants;
    private boolean submitted = false;
//    private Interview interview;
    private Job job;
    private Round round;
    private List<InterviewGroupObserver> observers;
    static private Logger logger = Logging.getLogger();

    public void addObserver(InterviewGroupObserver observer) {
        logger.info(this + "add observer " + observer);
        observers.add(observer);
    }

    public InterviewGroup(Job job, Interviewer interviewer, List<Applicant> applicants) {
        this.interviewer = interviewer;
        this.applicants = applicants;
        applicantsStatus = new HashMap<>();
        for (Applicant applicant : applicants) {
            applicantsStatus.put(applicant.getUsername(), false);
        }
        this.job = job;
        observers = new ArrayList<>();
    }

    public void submit() {
        logger.info(this + "submitted");
        submitted = true;
        Application application;
        for (InterviewGroupObserver observer : observers) {
            observer.updateOnGroupSubmitted(this);
        }
        for (Applicant applicant: applicants){
            application = applicant.getApplicationOf(job.getTitle()).get();
            application.getStatus().currentRoundFinished(round);
        }
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

    void setRound(Round round){
        this.round = round;
    }

    public void setApplicantPassed(String name, boolean passed) {
        applicantsStatus.put(name, passed);
    }

    public Job getJob() {
        return job;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getJob().getTitle() + " [");
        for (Applicant applicant : applicants) {
            builder.append(applicant.getUsername() + " ");
        }
        builder.append("]");
        return builder.toString();
    }
}
