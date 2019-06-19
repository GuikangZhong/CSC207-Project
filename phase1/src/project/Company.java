package project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class Company implements Serializable {
    private String name;

    public Collection<JobPosting> getJobPostings() {
        return Collections.unmodifiableCollection(jobPostings);
    }

    private Collection<JobPosting> jobPostings;
    private SystemClock clock;
    public Company(String name, SystemClock clock){
        this.name = name;
        this.clock = clock;
    }

    void addJobPosting(String title, int needed, LocalDateTime begin,
                       LocalDateTime end, Requirement requirement){
        JobPosting jobPosting = new JobPosting(new Job(title, this),begin, end);
        jobPostings.add(jobPosting);
    }
}
