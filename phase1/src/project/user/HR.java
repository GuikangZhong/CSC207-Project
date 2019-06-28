package project.user;

import project.application.Application;
import project.application.HireResult;
import project.application.Job;
import project.interview.IndividualInterviewProgress;
import project.interview.InterviewRecord;
import project.observer.HireResultObserver;

import java.util.ArrayList;
import java.util.List;

public class HR extends User implements HireResultObserver {
    private List<List<Application>> recommendationLists;
    // TODO: check with piazza / prof if this is really what it wants

    HR(UserHistory history, String username, String password) {
        super(history, username, password);
        recommendationLists = new ArrayList<>();
    }

    @Override
    public Type getType() {
        return Type.HR;
    }
    
    void setRecommendationLists (List<InterviewRecord> passed, List<InterviewRecord> failed){
        //the HR decides which applicants to hire or to fail and add the passed list to the field
        List<Application> recommendation = new ArrayList<>();
        for (InterviewRecord interviewee:passed) {
            interviewee.setPassed(true);
            IndividualInterviewProgress progress = interviewee.getApplication().getProgress();
            progress.setCurrentRoundFinished();
            recommendation.add(interviewee.getApplication());
        }
        for (InterviewRecord interviewee:failed) {
            interviewee.setPassed(false);
            IndividualInterviewProgress progress = interviewee.getApplication().getProgress();
            progress.setCurrentRoundFinished();
        }
        recommendationLists.add(recommendation);
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }

    @Override
    public void updateOnHireResult(Applicant applicant, Job job) {

    }
}
