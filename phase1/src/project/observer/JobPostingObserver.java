package project.observer;

import project.application.JobPosting;

public interface JobPostingObserver {
    /**
     * is called when jobPosting is closed
     * @param jobPosting
     */
    void updateOnJobPostingClosure(JobPosting jobPosting);
}
