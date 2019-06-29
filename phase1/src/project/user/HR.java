package project.user;

import project.application.*;
import project.interview.*;
import project.system.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class HR extends User {
    private Company company;
    private List<List<Application>> recommendationLists;
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

    void submit(List<InterviewRecord> passed) {
        //the HR decides which applicants to hire or to fail, add those passed to the field
        List<Application> recommendation = new ArrayList<>();
        for (InterviewRecord interviewee : passed) {
            recommendation.add(interviewee.getApplication());
        }
        recommendationLists.add(recommendation);
    }

    private List<List<Applicant>> getApplicants() {
        List<List<Applicant>> applicantList = new ArrayList<>();
        for (List<Application> applications : recommendationLists
        ) {
            List<Applicant> applicants = new ArrayList<>();
            for (Application application : applications) {
                applicants.add(application.getApplicant());
            }
            applicantList.add(applicants);
        }
        return applicantList;
    }

    void notifyJobPosting() {
        for (List<Applicant> applicants : getApplicants()) {
            Job job = recommendationLists.get(0).get(0).getJob();
            company.updateOnHireResult(applicants, job);
        }
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }


}
