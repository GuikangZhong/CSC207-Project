package project.interview;

import project.application.JobPosting;
import project.observer.HireResultObserver;
import project.observer.InterviewRoundFinishedObserver;
import project.user.Applicant;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Interview implements Serializable {
    private static final long serialVersionUID = -5626906039736330402L;
    private List<InterviewRoundFinishedObserver> interviewRoundFinishedObservers;
    private List<HireResultObserver> hireResultObservers;

    private InterviewSetup setup;
    private int round = 0;

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    private JobPosting jobPosting;

    public Interview() {
    }

    public String getHR() {
        return HR;
    }

    private String HR;

    InterviewStatus getStatus() {
        // TODO:
        return null;
    }

    List<Round> getRoundsFinished() {
        return Collections.unmodifiableList(setup.getRounds().subList(0, round));
    }

    List<Round> getRoundsInFuture() {
        return Collections.unmodifiableList(setup.getRounds().subList(round + 1, setup.getRounds().size()));
    }

    Round getRoundInProgress() {
        return setup.getRounds().get(round);

    }

    void updateOnRoundFinished() {
        for (InterviewRoundFinishedObserver observer : interviewRoundFinishedObservers) {
            observer.updateOnInterviewRoundFinished(this);
        }
    }

    void notifyHireResult(List<Applicant> applicants) {
        for (HireResultObserver observer : hireResultObservers) {
            observer.updateOnHireResult(applicants, jobPosting.getJob());
        }
    }

    public void addHireResultObserver(HireResultObserver observer) {
        hireResultObservers.add(observer);
    }

    public void addInterviewRoundFinishedObserver(InterviewRoundFinishedObserver observer) {
        interviewRoundFinishedObservers.add(observer);
    }
}
