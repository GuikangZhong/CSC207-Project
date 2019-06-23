package project;

import java.time.LocalDateTime;
import java.util.Collection;

public class JobPostingManager {
    public Collection<JobPosting> getJobPostings() {
        return jobPostings;
    }

    private Collection<JobPosting> jobPostings;
    void addJobPosting(JobPosting jobPosting){
        jobPostings.add(jobPosting);
    }
}
