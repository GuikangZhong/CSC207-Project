package project.user;

import project.application.*;
import project.interview.*;
import project.system.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class HR extends User {
    private Company company;
    private List<List<Application>> recommendationLists; // TODO: check with piazza / prof if this is really what it wants
    private List<String> interviewsRoundFinished; //List of job titles
    private List<String> interviewsToBeScheduled; //List of job titles
    private List<String> jobsHired; //List of job titles

    HR(UserHistory history, String username, String password, Company company) {
        super(history, username, password, company.getSystem().getClock());
        this.company = company;
        recommendationLists = new ArrayList<>();
    }

    public Company getCompany() {
        return company;
    }

    public List<String> getInterviewsRoundFinished() {
        return interviewsRoundFinished;
    }

    public void setInterviewsRoundFinished(List<String> interviewsRoundFinished) {
        this.interviewsRoundFinished = interviewsRoundFinished;
    }

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

    @Override
    public Type getType() {
        return Type.HR;
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }

    public void addJobsHired(String title) {
        jobsHired.add(title);
    }

    public void addInterviewRoundFinished(String jobTitle) {
        interviewsRoundFinished.add(jobTitle);
    }

    void addInterviewsToBeScheduled(String jobTitle) {
        interviewsToBeScheduled.add(jobTitle);
    }

    public void addRecommendationList(List<Application> recommendationList){
        recommendationLists.add(recommendationList);
    }

    public void setInterviews(List<InterviewAssignment> interviews, Interviewer interviewer){
        interviewer.setInterviews(interviews);
    }

}
