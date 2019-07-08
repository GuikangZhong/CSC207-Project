package project.user;

import project.application.Job;
import project.system.SystemClock;

import java.time.LocalDateTime;
import java.util.*;

public class ApplicantHistory extends UserHistory {
    private static final long serialVersionUID = -3949731953506050255L;

    private List<Job> jobApplied;
    private LocalDateTime lastApplicationClosed;
//    private List<Job> jobApplying;
    private List<Job> jobApplying;

    public ApplicantHistory(LocalDateTime now) {
        super(now);
        jobApplied = new ArrayList<>();
        jobApplying = new ArrayList<>();
//        jobApplying = new HashMap<>();
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
//    void addJobApplying(Job job){
//        jobApplying.put(job, "Submitted");
//    }

    void removeJobApplying(String name) {
        jobApplying.removeIf(e -> e.getTitle().equals(name));
    }
}
