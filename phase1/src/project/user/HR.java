package project.user;

import project.application.*;
import project.interview.*;
import project.system.SystemClock;
import project.utils.Logging;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HR extends User {
    private static final long serialVersionUID = 6752053563376828029L;

    private List<List<Application>> recommendationLists;
    private List<Interview> interviewsRoundFinished;
    private List<Interview> interviewsToBeScheduled;
    private List<String> jobsHired;  // How to store the name of the applicant hired??

    // TODO: check with piazza / prof if this is really what it wants
    public HR(UserHistory history,
              String username,
              String password,
              String realName,
              String company) {
        super(history, username, password, realName, company);
        recommendationLists = new ArrayList<>();
        interviewsRoundFinished = new ArrayList<>();
        interviewsToBeScheduled = new ArrayList<>();
    }

    public List<Interview> getInterviewsRoundFinished() {
        return interviewsRoundFinished;
    }


    public List<Interview> getInterviewsToBeScheduled() {
        return interviewsToBeScheduled;
    }


    public List<String> getJobsHired() {
        return jobsHired;
    }

    public void setJobsHired(List<String> jobsHired) {
        this.jobsHired = jobsHired;
    }

    @Override
    public final Type getType() {
        return Type.HR;
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }

    void addJobsHired(String title) {
        jobsHired.add(title);
    }

    void addInterviewRoundFinished(Interview interview) {
        interviewsRoundFinished.add(interview);
    }

    static private Logger logger = Logging.getLogger();

    void addInterviewsToBeScheduled(Interview interview) {
        logger.info("Added " + interview.getJob().getTitle() + " for " + getUsername());
        interviewsToBeScheduled.add(interview);
    }

}
