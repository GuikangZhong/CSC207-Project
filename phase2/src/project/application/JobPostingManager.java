package project.application;

import project.interview.Interview;
import project.observer.SystemObserver;
import project.system.MainSystem;
import project.user.Applicant;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

public class JobPostingManager implements Serializable, SystemObserver {
    private static final long serialVersionUID = -9197333240356088957L;
    private HashMap<String, JobPosting> jobPostings;
    private Company company;
    private MainSystem system;
    static private Logger logger = Logging.getLogger();

    public JobPostingManager(MainSystem system, Company company) {
        jobPostings = new HashMap<>();
        this.company = company;
        this.system = system;
    }

    public Company getCompany() {
        return company;
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
        String jobName = jobPosting.getJobTitle();
        if (!jobPostings.containsKey(jobName)) {
            logger.info("Added job " + jobPosting.getJobTitle());
            jobPostings.put(jobName, jobPosting);
            jobPosting.addObserver(company.getHrManager());
            return true;
        }
        return false;
    }

    public JobPosting getJobPosting(String title) {
        return jobPostings.get(title);
    }

//    @Override
//    public void updateOnInterviewRoundFinished(Interview interview) {
//
//    }

//    @Override
//    public void updateOnHireResult(Interview interview) {
//        String name = interview.getJobPosting().getJobTitle();
//        JobPosting jobPosting = getJobPosting(name);
//        // if the size is 0, it means no one is hired for this posting
//        if (interview.getApplicants().size() == 0) {
//            jobPosting.addHired(null);
//        } else {
//            for (Applicant applicant : interview.getApplicants()) {
//                jobPosting.addHired(applicant);
//                applicant.moveToApplied(interview.getJobPosting());
//                applicant.addHired(interview.getJobPosting());
//            }
//        }
//    }

//    @Override
//    public void updateOnNoMoreRounds(Interview interview) {
//
//    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        for (JobPosting jobPosting : jobPostings.values()) {
            jobPosting.updateOnTime(now);
        }
    }
}
