package project.user;

import project.application.Application;
import project.application.HireResult;
import project.application.Job;
import project.application.JobPosting;
import project.interview.IndividualInterviewProgress;
import project.interview.InterviewRecord;
import project.observer.HireResultObserver;
import project.observer.HireResultObserverHR;
import project.system.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class HR extends User implements HireResultObserverHR {
    private List<List<Application>> recommendationLists;
    // TODO: check with piazza / prof if this is really what it wants

    HR(UserHistory history, String username, String password, SystemClock clock) {
        super(history, username, password, clock);
        recommendationLists = new ArrayList<>();
    }

    @Override
    public Type getType() {
        return Type.HR;
    }

    @Override
    public void updateOnHireResult(List<InterviewRecord> passed, List<InterviewRecord> failed, JobPosting jobPosting) {
        //the HR decides which applicants to hire or to fail, add those passed to the field and notify them
        List<Application> recommendation = new ArrayList<>();
        for (InterviewRecord interviewee:passed) {
            interviewee.setPassed(true);
            recommendation.add(interviewee.getApplication());
            jobPosting.addHired(interviewee.getApplication().getApplicant());
        }
        for (InterviewRecord interviewee:failed) {
            interviewee.setPassed(false);
        }
        recommendationLists.add(recommendation);
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }

}
