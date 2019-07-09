package project.application;

import project.interview.Interview;
import project.observer.InterviewObserver;
import project.observer.SystemObserver;
import project.system.MainSystem;
import project.system.SystemClock;
import project.user.Applicant;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class JobPostingManager implements InterviewObserver, Serializable, SystemObserver {
    private static final long serialVersionUID = -9197333240356088957L;
    private HashMap<String, JobPosting> jobPostings;

    public Company getCompany() {
        return company;
    }

    private Company company;
    private MainSystem system;

    public JobPostingManager(MainSystem system, Company company) {
        jobPostings = new HashMap<>();
        this.company = company;
        this.system = system;
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
            logger.info("Added job " + jobPosting.getJobTitle());
            jobPostings.put(jobName, jobPosting);
            jobPosting.addObserver(company.getHrManager());
            return true;
        }
        return false;
    }
    static private Logger logger = Logging.getLogger();
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

    public List<JobPosting> getJobPostingsForHR(String hr) {
        List<JobPosting> jobPostingList = new ArrayList<>();
        for (JobPosting jobPosting: jobPostings.values()) {
            if (jobPosting.getHr().getUsername().equals(hr)) {
                jobPostingList.add(jobPosting);
            }
        }
        return jobPostingList;
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
        for (JobPosting jobPosting : jobPostings.values()) {
            jobPosting.updateOnTime(now);
        }
    }
}
