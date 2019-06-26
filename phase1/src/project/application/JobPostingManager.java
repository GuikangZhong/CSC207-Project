package project.application;

import project.user.Applicant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobPostingManager {
    private HashMap<String, JobPosting> jobPostings;

    public HashMap<String, JobPosting> getJobPostings() {
        return jobPostings;
    }

    /**
     *
     * @param jobPosting needs to be added
     * @return 1 if successfully added
     *  0 if job posting already exists
     */
    int addJobPosting(JobPosting jobPosting){
        String jobName = jobPosting.getJob().getTitle();
        if (!jobPostings.containsKey(jobName)){
            jobPostings.put(jobName, jobPosting);
            return 1;
        }
        return 0;
    }

    void removeJobPosting(String name){
        jobPostings.remove(name);
    }

    void removeApplication(Application application){
        String name = application.getJob().getTitle();
        JobPosting jobPosting = getJobPostings().get(name);
        jobPosting.removeApplication(application);
    }

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
