package project;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

public class Company implements Serializable {
    private String name;
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
