package project.user;

import project.application.*;
import project.interview.*;
import project.system.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class HR extends User {
    public Company getCompany() {
        return company;
    }

    private Company company;
    private List<List<Application>> recommendationLists;

    public List<String> getInterviewsRoundFinished() {
        return interviewsRoundFinished;
    }

    public void setInterviewsRoundFinished(List<String> interviewsRoundFinished) {
        this.interviewsRoundFinished = interviewsRoundFinished;
    }

    private List<String> interviewsRoundFinished;
    private List<String> interviewsToBeScheduled;
    private List<String> jobsHired;
    public List<String> getInterviewsToBeScheduled() {
        return interviewsToBeScheduled;
    }

    public void setInterviewsToBeScheduled(List<String> interviewsToBeScheduled) {
        this.interviewsToBeScheduled = interviewsToBeScheduled;
    }

    public List<String> getJobsHired() {
        return jobsHired;
    }

    public void setJobsHired(List<String> jobsHired) {
        this.jobsHired = jobsHired;
    }


    // TODO: check with piazza / prof if this is really what it wants

    HR(UserHistory history, String username, String password, Company company) {
        super(history, username, password, company.getSystem().getClock());
        this.company = company;
        recommendationLists = new ArrayList<>();
    }

    @Override
    public Type getType() {
        return Type.HR;
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }

    void addJobsHired(String title) {
        jobsHired.add(title);
    }
    void addInterviewRoundFinished(String jobTitle){
        interviewsRoundFinished.add(jobTitle);
    }
    void addInterviewsToBeScheduled(String jobTitle) {
        interviewsToBeScheduled.add(jobTitle);
    }

}
