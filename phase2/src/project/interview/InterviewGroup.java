package project.interview;

import project.application.JobPosting;
import project.observer.InterviewGroupObserver;
import project.user.Applicant;
import project.user.Interviewer;
import project.utils.Logging;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

/**
 * An interview group means in a round, a group with multiple applicants and one interviewers
 */
public class InterviewGroup implements Serializable {
    private static final long serialVersionUID = 4355176965416536403L;
    private Interviewer interviewer;
    private Map<String, Boolean> applicantsStatus;
    private List<Applicant> applicants;
    private boolean submitted = false;
    private JobPosting jobPosting;
    private Round round;
    private List<InterviewGroupObserver> observers;
    static private Logger logger = Logging.getLogger();

    void addObserver(InterviewGroupObserver observer) {
        logger.info(this + "add observer " + observer);
        observers.add(observer);
    }

    public InterviewGroup(JobPosting jobPosting, Interviewer interviewer, List<Applicant> applicants) {
        this.interviewer = interviewer;
        this.applicants = applicants;
        applicantsStatus = new HashMap<>();
        for (Applicant applicant : applicants) {
            applicantsStatus.put(applicant.getUsername(), false);
        }
        this.jobPosting = jobPosting;
        observers = new ArrayList<>();
    }

    public List<Applicant> getApplicants() {
        return Collections.unmodifiableList(applicants);
    }

    /**
     * This function will be called if interviewer finished promoting applicants
     * to the next round
     */
    public void submit() {
        logger.info(this + "submitted");
        submitted = true;
        for (InterviewGroupObserver observer : observers) {
            observer.updateOnGroupSubmitted(this);
        }
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    boolean isSubmitted() {
        return submitted;
    }

    List<Applicant> getApplicantsPassed() {
        List<Applicant> result = new ArrayList<>();
        for (Applicant applicant : applicants) {
            if (applicantsStatus.get(applicant.getUsername())) {
                result.add(applicant);
            }
        }
        return result;
    }

    public boolean isApplicantPassed(Applicant applicant){
        return applicantsStatus.get(applicant.getUsername());
    }

    public Round getRound() {
        return round;
    }

    void setRound(Round round) {
        this.round = round;
    }

    public void setApplicantPassed(String name, boolean passed) {
        applicantsStatus.put(name, passed);
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getJobPosting().getJobTitle() + " [");
        for (Applicant applicant : applicants) {
            builder.append(applicant.getUsername() + " ");
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Remove the applicant withdrawing the application from the applicants group.
     * @param applicant: the applicant that has withdrawn from the job that this interview group correspond to
     */
    void withdraw(Applicant applicant) {
        if (applicants.remove(applicant)) {
            logger.info("Removed " + applicant.getUsername());
        }
    }

    void updateInterviewerMessage(){
        interviewer.removeInterviewGroup(this);
    }
}
