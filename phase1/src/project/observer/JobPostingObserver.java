package project.observer;

import project.application.JobPosting;

public interface JobPostingObserver {
    void updateOnJobPostingClosure(JobPosting jobPosting);
}
