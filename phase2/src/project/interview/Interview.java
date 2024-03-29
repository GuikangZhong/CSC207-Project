package project.interview;

import project.application.Application;
import project.application.JobPosting;
import project.observer.ApplicantObserver;
import project.observer.InterviewObserver;
import project.observer.RoundObserver;
import project.user.Applicant;
import project.user.HR;
import project.user.Interviewer;
import project.utils.Logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Interview contains many rounds. Such as one phone and three in person rounds.
 */
public class Interview implements Serializable, RoundObserver, ApplicantObserver {
    private static final long serialVersionUID = -5626906039736330402L;
    private Collection<InterviewObserver> observers;
    private HR hr;
    private List<Applicant> applicants;
    private InterviewSetup setup;
    private int round;
    private JobPosting jobPosting;
    private int numberNeeded;
    private static Logger logger = Logging.getLogger();

    public Interview(HR hr,
                     JobPosting jobPosting,
                     InterviewSetup setup) {
        if(setup.isTemplate()){
            throw new RuntimeException("You need to call InterviewSetup.clone() !!!");
        }
        this.hr = hr;
        this.setup = setup;
        this.jobPosting = jobPosting;
        numberNeeded = jobPosting.getnApplicantNeeded();
        applicants = new ArrayList<>();
        for (Application application : jobPosting.getApplications()) {
            applicants.add(application.getApplicant());
            application.getApplicant().addObserver(this);
        }
        round = 0;
        observers = new ArrayList<>();
        getRoundInProgress().addObserver(this);
    }

    public HR getHR() {
        return hr;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public int getNumberNeeded() {
        return numberNeeded;
    }

    public List<Applicant> getApplicants() {
        return Collections.unmodifiableList(applicants);
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

    /**
     * this function is called when the max round is finished, there are still a few applicants left
     * @param applicants: The final applicants left after the max number of rounds has been reached.
     */
    public void hireFromFinalCandidates(List<Applicant> applicants) {
        if (hasNextRound()) {
            throw new RuntimeException("You shouldn't use recommendation lst now");
        }
        if (applicants.size() > numberNeeded) {
            throw new RuntimeException("You cannot assign more than needed");
        }
        this.applicants = applicants;
        automaticHire();
        applicantsRemoveObserver();
    }

    /**
     * Make the applicants in the interview no longer this interview as an observer
     */
    private void applicantsRemoveObserver() {
        for (Applicant applicant : applicants) {
            applicant.removeObserver(this);
        }
    }

    public void addObserver(InterviewObserver observer) {
        logger.info("Interview: " + jobPosting.getJobTitle() + " added observer " + observer);
        observers.add(observer);
    }

    private void toNextRound() {
        if (round != -1) {
            if (!getRoundInProgress().isAllGroupsSubmitted() || round == setup.getRounds().size() - 1) {
                throw new RuntimeException("You have overflowed the rounds or skipped a round");
            }

        }
        round++;
        getRoundInProgress().addObserver(this);
    }

    /**
     * filter all the applicants who have been forwarded to the future rounds from the current round of interview
     * in progress.
     */
    private void filterPassed() {
        applicants = getRoundInProgress().getApplicantsPassed();
    }

    public void assignRound(InterviewGroupAssignmentStrategy strategy, List<Interviewer> interviewers) {
        logger.info("Job" + jobPosting.getJobTitle() + " Round: " + getRoundInProgress() + " assigned");
        List<InterviewGroup> groups = strategy.select(applicants, interviewers);
        logger.info("Job" + jobPosting.getJobTitle() + " Round: "
                + getRoundInProgress() + " assigned successful");
        for (InterviewGroup group : groups) {
            logger.info(group.toString());
            Interviewer interviewer = group.getInterviewer();
            interviewer.addInterviewGroup(group);
            group.addObserver(interviewer);
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

    private void updateOnRoundFinished() {
        if (applicants.size() <= numberNeeded) {
            automaticHire();
            applicantsRemoveObserver();
        } else if (hasNextRound()) {
            for (InterviewObserver observer : observers) {
                observer.updateOnInterviewRoundFinished(this);
            }
            toNextRound();
        } else {
            for (InterviewObserver observer : observers) {
                observer.updateOnNoMoreRounds(this);
            }
        }
    }

    /**
     * This method will only get called when the number of applicants remaining in applicant pool is less than or equal
     * to the number of candidates needed for the job.
     */
    private void automaticHire(){
        for (Applicant applicant: applicants){
            applicant.addHired(getJobPosting());
        }
        jobPosting.addHired(applicants);
        applicantsRemoveObserver();
        updateInterviewerMessage();
    }

    @Override
    public void updateOnRoundFinished(Round round) {
        logger.info("Interview for " + jobPosting.getJobTitle() + "round finished");
        filterPassed();
        updateOnRoundFinished();
    }

    @Override
    public void updateOnApplicationWithdraw(Application application) {
        if(application.getJobPosting() != jobPosting)return;
        if (!applicants.remove(application.getApplicant())) {
            throw new RuntimeException("You have removed someone doesn't exist!!!");
        }
        getRoundInProgress().withdraw(application.getApplicant());
        if (applicants.size() <= numberNeeded) {
            automaticHire();
        }
    }

    /**
     * Gets called that the interview automatically ends, and the interview group will no longer be viewed by
     * the corresponding interviewer.
     */
    private void updateInterviewerMessage(){
        Round round = getRoundInProgress();
        for (InterviewGroup group: round.getGroups()){
            group.updateInterviewerMessage();
        }
    }


}
