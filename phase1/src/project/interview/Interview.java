package project.interview;

import project.application.Application;
import project.application.JobPosting;
import project.observer.InterviewObserver;
import project.observer.RoundObserver;
import project.user.Applicant;
import project.user.Interviewer;
import project.utils.Logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Interview implements Serializable, RoundObserver {
    private static final long serialVersionUID = -5626906039736330402L;
    private Collection<InterviewObserver> observers;
    private List<Applicant> applicants;
    private InterviewSetup setup;
    private int round;

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    private JobPosting jobPosting;

    public Interview(String HR,
                     JobPosting jobPosting,
                     InterviewSetup setup) {
        this.HR = HR;
        this.setup = setup;
        this.jobPosting = jobPosting;
        applicants = new ArrayList<>();
        for (Application application : jobPosting.getApplications()) {
            applicants.add(application.getApplicant());
        }
        round = -1;
        observers = new ArrayList<>();
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
        for (InterviewObserver observer : observers) {
            observer.updateOnInterviewRoundFinished(this);
        }
    }

    private void notifyHireResult(List<Applicant> applicants) {
        for (InterviewObserver observer : observers) {
            observer.updateOnHireResult(applicants, jobPosting.getJob());
        }
    }

    private static Logger logger = Logging.getLogger();

    public void addObserver(InterviewObserver observer) {
        logger.info("Interview: " + getJobPosting().getJobTitle() + " added observer " + observer);
        observers.add(observer);
    }

    public void toNextRound() {
        if(round != -1) {
            if (!getRoundInProgress().isAllGroupsSubmitted() || round == setup.getRounds().size() - 1) {
                throw new RuntimeException("You have overflowed the rounds or skipped a round");
            }
            filterPassed();
        }
        round++;
        getRoundInProgress().addObserver(this);

    }

    void filterPassed() {
        applicants = getRoundInProgress().getApplicantsPassed();
    }

    public void assignRound(InterviewGroupAssignmentStrategy strategy, List<Interviewer> interviewers) {
        logger.info("Job" + getJobPosting().getJobTitle() + " Round: " + getRoundInProgress() + " assigned");
        List<InterviewGroup> groups = strategy.select(applicants, interviewers);
        for (InterviewGroup group : groups) {
            logger.info(group.toString());
            Interviewer interviewer = group.getInterviewer();
            interviewer.addInterviewGroup(group);
        }
        getRoundInProgress().setGroups(groups);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Set Interview for " + jobPosting.getJobTitle() + " HR: " + getHR() + '\n');
        for (Round round : setup.getRounds()) {
            s.append(round + "\n");
        }
        return s.toString();
    }

    @Override
    public void updateOnRoundFinished(Round round) {
        logger.info("Interview for " + jobPosting.getJobTitle() + "round finished");
        updateOnRoundFinished();
    }
}
