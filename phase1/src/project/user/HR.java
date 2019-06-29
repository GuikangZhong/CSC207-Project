package project.user;

import project.application.*;
import project.interview.*;
import project.system.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class HR extends User implements InterviewMatcher, InterviewMatchingChecker {
    private Company company;
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

    void submit(List<InterviewRecord> passed) {
        //the HR decides which applicants to hire or to fail, add those passed to the field
        List<Application> recommendation = new ArrayList<>();
        for (InterviewRecord interviewee:passed) {
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
        for (List<Applicant> applicants : getApplicants()
        ) {
            Job job = recommendationLists.get(0).get(0).getJob();
            JobPostingManager jobPostingManager = company.getJobPostingManager();
            jobPostingManager.updateOnHireResult(applicants, job);
        }
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }


    @Override
    public List<InterviewAssignment> match(List<Interviewer> interviewers, List<InterviewRecord> interviewees, Interview interview) {
        List<InterviewAssignment> assignments = new ArrayList<>();
        for (Interviewer interviewer : interviewers
        ) {
            for (InterviewRecord interviewee : interviewees
            ) {
                List<Applicant> applicants = new ArrayList<>();
                Applicant applicant = interviewee.getApplication().getApplicant();
                if (isPossibleMatching(interviewer, applicant, interview)) {
                    applicants.add(applicant);
                }

            }
//            TODO: not finished. needs to judge if an interviewee is already assigned.
        }
        return assignments;
    }

    @Override
    public boolean isPossibleMatching(Interviewer interviewer, Applicant applicant, Interview interview) {
        // TODO: how to judge if its possible?
        boolean result = true;
        for (InterviewAssignment assignment : interview.getAssignments()) {
            for (InterviewRecord record : assignment.getInterviewees()) {
                if (record.getApplication().getApplicant().equals(applicant)) {
                    result = false;
                }
            }
        }
        return result;
    }

//    TODO: Are new interviews created here by HR or in the System?
}
