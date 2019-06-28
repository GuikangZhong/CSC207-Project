package project.application;

import project.system.SystemClock;
import project.user.HR;
import project.user.HRManager;
import project.user.InterviewerManager;

import java.io.Serializable;

public class Company implements Serializable {
    private String name;
    private JobPostingManager jobPostingManager;
    private HRManager hrManager;
    private InterviewerManager interviewerManager;
    private SystemClock clock;

    public Company(String name, SystemClock clock) {
        this.name = name;
        this.clock = clock;
        jobPostingManager = new JobPostingManager(clock);
        hrManager = new HRManager();
        interviewerManager = new InterviewerManager();
    }

    public String getName() {
        return name;
    }

    public JobPostingManager getJobPostingManager() {
        return jobPostingManager;
    }

    public HRManager getHrManager() {
        return hrManager;
    }

    public InterviewerManager getInterviewerManager() {
        return interviewerManager;
    }

}
