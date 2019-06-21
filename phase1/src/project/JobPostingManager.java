package project;

import java.util.List;

class JobPostingManager {
    private List<JobPosting> jobPostings;

    public void setJobPostings(List<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    public List<JobPosting> getJobPostings() {
        return jobPostings;
    }
}
