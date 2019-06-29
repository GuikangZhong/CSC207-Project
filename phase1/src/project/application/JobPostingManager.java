package project.application;

import project.observer.JobPostingClosureObserver;
import project.system.SystemClock;
import project.user.Applicant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.observer.HireResultObserver;

public class JobPostingManager implements HireResultObserver, JobPostingClosureObserver {
    private HashMap<String, JobPosting> jobPostings;
    private SystemClock clock;

    public JobPostingManager(SystemClock clock) {
        jobPostings = new HashMap<>();
        this.clock = clock;
    }

    public HashMap<String, JobPosting> getJobPostings() {
        return jobPostings;
    }

    /**
     * @param jobPosting needs to be added
     * @return true if successfully added
     * false if job posting already exists
     */
    boolean addJobPosting(JobPosting jobPosting) {
        String jobName = jobPosting.getJob().getTitle();
        if (!jobPostings.containsKey(jobName)) {
            jobPostings.put(jobName, jobPosting);
            return true;
        }
        return false;
    }

    void removeJobPosting(String name) {
        jobPostings.remove(name);
    }

    void removeApplication(Application application) {
        String name = application.getJob().getTitle();
        JobPosting jobPosting = getJobPostings().get(name);
        jobPosting.removeApplication(application);
    }

    List<Applicant> getAllApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        for (JobPosting jobPosting : jobPostings.values()) {
            for (Application application : jobPosting.getApplications()) {
                applicants.add(application.getApplicant());
            }
        }
        return applicants;
    }

    JobPosting getJobPosting(String title) {
        return jobPostings.get(title);
    }

    @Override
    public void updateOnHireResult(List<Applicant> applicants, Job job) {
        String name = job.getTitle();
        JobPosting jobPosting = getJobPosting(name);
        for (Applicant applicant : applicants) {
            jobPosting.addHired(applicant);
        }
    }

    @Override
    public void updateOnJobPostingClosure(String jobName) {
        JobPosting jobPosting = getJobPosting(jobName);
        jobPosting.setStatus(JobPosting.Status.CLOSED);
    }
}
