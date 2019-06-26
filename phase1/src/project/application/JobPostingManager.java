package project.application;

import project.user.Applicant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class JobPostingManager {

    public HashMap<String, JobPosting> getJobPostings() {
        return jobPostings;
    }

    private HashMap<String, JobPosting> jobPostings;

    List<Applicant> getAllApplicants(){
        List<Applicant> applicants = new ArrayList<>();
        for(JobPosting jobPosting : jobPostings.values()){
            for(Application application : jobPosting.getApplications()){
                applicants.add(application.getApplicant());
            }
        }
        return applicants;
    }
}
