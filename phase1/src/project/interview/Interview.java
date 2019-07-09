package project.interview;

import project.application.Application;
import project.application.Job;
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

    public List<Applicant> getApplicants() {
        return Collections.unmodifiableList(applicants);
    }

    private List<Applicant> applicants;
    private InterviewSetup setup;
    private int round;

    public Job getJob() {
        return job;
    }

    private Job job;
    private int numberNeeded;

    public Interview(String HR,
                     JobPosting jobPosting,
                     InterviewSetup setup) {
        this.HR = HR;
        this.setup = setup;
        this.job = jobPosting.getJob();
        numberNeeded = jobPosting.getnApplicantNeeded();
        applicants = new ArrayList<>();
        for (Application application : jobPosting.getApplications()) {
            applicants.add(application.getApplicant());
        }
        round = 0;
        observers = new ArrayList<>();
        getRoundInProgress().addObserver(this);
    }

    public String getHR() {
        return HR;
    }

    private String HR;


    public List<Round> getRoundsFinished() {
        return Collections.unmodifiableList(setup.getRounds().subList(0, round));
    }

    public List<Round> getRoundsInFuture() {
        return Collections.unmodifiableList(setup.getRounds().subList(round + 1, setup.getRounds().size()));
    }

    public int getRound() {
        return round;
    }

    public boolean hasNextRound() {
        return round + 1 < setup.getTotalRoundCount();
    }

    public Round getRoundInProgress() {
        if (round == -1) {
            throw new RuntimeException("You haven't started this interview yet");
        }
        return setup.getRounds().get(round);
    }

    void updateOnRoundFinished() {
        if (applicants.size() <= numberNeeded) {
            for (InterviewObserver observer : observers) {
                observer.updateOnHireResult(applicants, job);
            }
        } else {
            for (InterviewObserver observer : observers) {
                observer.updateOnInterviewRoundFinished(this);
            }
        }
        if (hasNextRound()) {
            toNextRound();
        }
    }

    private void notifyHireResult(List<Applicant> applicants) {
        for (InterviewObserver observer : observers) {
            observer.updateOnHireResult(applicants, job);
        }
    }

    private static Logger logger = Logging.getLogger();

    public void addObserver(InterviewObserver observer) {
        logger.info("Interview: " + job.getTitle() + " added observer " + observer);
        observers.add(observer);
    }

    private void toNextRound() {
        if (round != -1) {
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
        logger.info("Job" + job.getTitle() + " Round: " + getRoundInProgress() + " assigned");
        List<InterviewGroup> groups = strategy.select(applicants, interviewers);
        logger.info("Job" + job.getTitle() + " Round: " + getRoundInProgress() + " assigned successful");
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
        s.append("Set Interview for " + job.getTitle() + " HR: " + getHR() + '\n');
        for (Round round : setup.getRounds()) {
            s.append(round + "\n");
        }
        return s.toString();
    }

    @Override
    public void updateOnRoundFinished(Round round) {
        logger.info("Interview for " + job.getTitle() + "round finished");
        updateOnRoundFinished();
    }
}
