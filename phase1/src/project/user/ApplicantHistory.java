package project.user;

import project.application.Job;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicantHistory extends UserHistory {
    private static final long serialVersionUID = -3949731953506050255L;
    private List<Job> jobApplied;
    private LocalDateTime lastApplicationClosed;
    private List<Job> jobApplying;


    public ApplicantHistory(Clock clock) {
        super(clock);
        jobApplied = new ArrayList<>();
        jobApplying = new ArrayList<>();
        lastApplicationClosed = null;
    }

    public List<Job> getJobApplied() {
        return Collections.unmodifiableList(jobApplied);
    }

    public List<Job> getJobApplying() {
        return Collections.unmodifiableList(jobApplying);
    }

    public LocalDateTime getLastApplicationClosed() {
        return lastApplicationClosed;
    }


    void addJobApplied(Job job) {
        jobApplied.add(job);
    }

    void removeJobApplied(String name) {
        for (Job job : jobApplied) {
            if (job.getTitle().equals(name)) {
                jobApplied.remove(job);
            }
        }
    }


    void addJobApplying(Job job) {
        jobApplying.add(job);
    }

    void removeJobApplying(String name) {
        for (Job job : jobApplying) {
            if (job.getTitle().equals(name)) {
                jobApplying.remove(job);
            }
        }
    }
}
