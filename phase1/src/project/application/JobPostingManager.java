package project.application;

import project.interview.Interview;
import project.observer.InterviewObserver;
import project.observer.SystemObserver;
import project.system.SystemClock;
import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobPostingManager implements InterviewObserver, Serializable, SystemObserver {
    private static final long serialVersionUID = -9197333240356088957L;
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
    public boolean addJobPosting(JobPosting jobPosting) {
        String jobName = jobPosting.getJob().getTitle();
        if (!jobPostings.containsKey(jobName)) {
            jobPostings.put(jobName, jobPosting);
            return true;
        }
        return false;
    }

    public void removeJobPosting(String name) {
        jobPostings.remove(name);
    }

    public void removeApplication(Application application) {
        String name = application.getJob().getTitle();
        JobPosting jobPosting = getJobPostings().get(name);
        jobPosting.removeApplication(application);
    }

    public List<Applicant> getAllApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        for (JobPosting jobPosting : jobPostings.values()) {
            for (Application application : jobPosting.getApplications()) {
                applicants.add(application.getApplicant());
            }
        }
        return applicants;
    }

    public JobPosting getJobPosting(String title) {
        return jobPostings.get(title);
    }

    @Override
    public void updateOnInterviewRoundFinished(Interview interview) {

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
    public void updateOnTime(LocalDateTime now) {

    }
}
