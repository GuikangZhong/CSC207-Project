package project.application;

import project.interview.InterviewProgress;
import project.observer.HireResultObserver;
import project.observer.InterviewRoundFinishedObserver;
import project.observer.JobPostingClosureObserver;
import project.observer.SystemTimeUpdateObserver;
import project.system.MainSystem;
import project.system.SystemClock;
import project.user.Applicant;
import project.user.HR;
import project.user.HRManager;
import project.user.InterviewerManager;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Company
        implements Serializable,
        HireResultObserver,
        InterviewRoundFinishedObserver,
        JobPostingClosureObserver, SystemTimeUpdateObserver {
    private static final long serialVersionUID = 2088083308860080279L;
    private String name;
    private JobPostingManager jobPostingManager;
    private HRManager hrManager;
    private InterviewerManager interviewerManager;

    public MainSystem getSystem() {
        return system;
    }

    private MainSystem system;

    public Company(String name, MainSystem system) {
        this.name = name;
        this.system = system;
        jobPostingManager = new JobPostingManager(system.getClock());
        hrManager = new HRManager(system, this);
        interviewerManager = new InterviewerManager(system, this);
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

    @Override
    public void updateOnHireResult(List<Applicant> applicants, Job job) {
        getJobPostingManager().updateOnHireResult(applicants, job);
    }

    @Override
    public void updateOnInterviewRoundFinished(InterviewProgress progress) {
        hrManager.updateOnInterviewRoundFinished(progress);
    }

    @Override
    public void updateOnJobPostingClosure(String jobTitle) {
        hrManager.updateOnJobPostingClosure(jobTitle);
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        jobPostingManager.updateOnTime(now);
    }
}
