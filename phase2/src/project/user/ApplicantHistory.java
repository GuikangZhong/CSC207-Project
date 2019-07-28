package project.user;

import project.application.Job;
import project.application.JobPosting;
import project.interview.Round;
import project.observer.JobPostingObserver;

import java.time.LocalDateTime;
import java.util.*;

public class ApplicantHistory extends UserHistory implements JobPostingObserver {
    private static final long serialVersionUID = -3949731953506050255L;

    private List<Job> jobApplied;
    private LocalDateTime lastApplicationClosed;
    private LocalDateTime latestClosedDate;
    // the Boolean values indicate whether the job posting has been closed (true) or not,
    private Map<Job, Boolean> jobApplying;
    private Map<Job, Round> applicationsInProgress;
    private List<Job> applicationsRejected;
    private List<Job> hiredPositions;

    public ApplicantHistory(LocalDateTime now) {
        super(now);
        jobApplied = new ArrayList<>();
        jobApplying = new HashMap<>();
        lastApplicationClosed = null;
        latestClosedDate = null;
        applicationsInProgress = new HashMap<>();
        applicationsRejected = new ArrayList<>();
        hiredPositions = new ArrayList<>();
    }

    public List<Job> getJobApplied() {
        return Collections.unmodifiableList(jobApplied);
    }

    public Set<Job> getJobApplying() {
        return Collections.unmodifiableSet(jobApplying.keySet());
    }

    public List<Job> getApplicationsRejected(){
        return Collections.unmodifiableList(applicationsRejected);
    }

    public List<Job> getHiredPositions(){
        return Collections.unmodifiableList(hiredPositions);
    }

    public Map<Job, Round> getApplicationsInProgress(){
        return Collections.unmodifiableMap(applicationsInProgress);
    }

    public LocalDateTime getLastApplicationClosed() {
        return lastApplicationClosed;
    }

    void addJobApplied(Job job) {
        if (!jobApplied.contains(job))
            jobApplied.add(job);
    }

    void addJobApplying(Job job) {
        jobApplied.remove(job);
        jobApplying.put(job, false);
        latestClosedDate = lastApplicationClosed;
        lastApplicationClosed = null;

    }

    void removeJobApplying(Job job) {
        jobApplying.remove(job);
        if ((allApplicationClosed() && latestClosedDate != null) &&
                (lastApplicationClosed == null || lastApplicationClosed.isBefore(latestClosedDate))) {
            lastApplicationClosed = latestClosedDate;
        }
    }

    void addApplicationInProgress(Job job, Round round){
        applicationsInProgress.put(job, round);
    }

    void addApplicationRejected(Job job){
        applicationsRejected.add(job);
    }

    void addHiredPositions(Job job){
        hiredPositions.add(job);
    }

    private boolean allApplicationClosed() {
        return ((jobApplying.isEmpty()) || !(jobApplying.values().contains(false)));
    }

    @Override
    public void updateOnJobPostingClosure(JobPosting jobPosting) {
        jobApplying.put(jobPosting.getJob(), true);
        if (latestClosedDate == null || latestClosedDate.isBefore(jobPosting.getCloseDate()))
            latestClosedDate = jobPosting.getCloseDate();
        if (allApplicationClosed()) {
            if (lastApplicationClosed == null || lastApplicationClosed.isBefore(jobPosting.getCloseDate()))
                lastApplicationClosed = jobPosting.getCloseDate();
        }
    }

    void removeInProgress(Job job){
        applicationsInProgress.remove(job);
    }
}
