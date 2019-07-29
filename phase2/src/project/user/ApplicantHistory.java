package project.user;

import project.application.JobPosting;
import project.interview.Round;
import project.observer.JobPostingObserver;

import java.time.LocalDateTime;
import java.util.*;

public class ApplicantHistory extends UserHistory implements JobPostingObserver {
    private static final long serialVersionUID = -3949731953506050255L;

    private List<JobPosting> jobApplied;
    private LocalDateTime lastApplicationClosed;
    private LocalDateTime latestClosedDate;
    // the Boolean values indicate whether the job posting has been closed (true) or not,
    private Map<JobPosting, Boolean> jobApplying;
    private Map<JobPosting, Round> applicationsInProgress;
    private List<JobPosting> applicationsRejected;
    private List<JobPosting> hiredPositions;

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

    public List<JobPosting> getJobApplied() {
        return Collections.unmodifiableList(jobApplied);
    }

    public Set<JobPosting> getJobApplying() {
        return Collections.unmodifiableSet(jobApplying.keySet());
    }

    public List<JobPosting> getApplicationsRejected(){
        return Collections.unmodifiableList(applicationsRejected);
    }

    public List<JobPosting> getHiredPositions(){
        return Collections.unmodifiableList(hiredPositions);
    }

    public Map<JobPosting, Round> getApplicationsInProgress(){
        return Collections.unmodifiableMap(applicationsInProgress);
    }

    public LocalDateTime getLastApplicationClosed() {
        return lastApplicationClosed;
    }

    void addJobApplied(JobPosting job) {
        if (!jobApplied.contains(job))
            jobApplied.add(job);
    }

    void addJobApplying(JobPosting job) {
        jobApplied.remove(job);
        jobApplying.put(job, false);
        latestClosedDate = lastApplicationClosed;
        lastApplicationClosed = null;

    }

    void removeJobApplying(JobPosting job) {
        jobApplying.remove(job);
        if ((allApplicationClosed() && latestClosedDate != null) &&
                (lastApplicationClosed == null || lastApplicationClosed.isBefore(latestClosedDate))) {
            lastApplicationClosed = latestClosedDate;
        }
    }

    void addApplicationInProgress(JobPosting job, Round round){
        applicationsInProgress.put(job, round);
    }

    void addApplicationRejected(JobPosting job){
        applicationsRejected.add(job);
    }

    void addHiredPositions(JobPosting job){
        hiredPositions.add(job);
    }

    private boolean allApplicationClosed() {
        return ((jobApplying.isEmpty()) || !(jobApplying.values().contains(false)));
    }

    @Override
    public void updateOnJobPostingClosure(JobPosting jobPosting) {
        jobApplying.put(jobPosting, true);
        if (latestClosedDate == null || latestClosedDate.isBefore(jobPosting.getCloseDate()))
            latestClosedDate = jobPosting.getCloseDate();
        if (allApplicationClosed()) {
            if (lastApplicationClosed == null || lastApplicationClosed.isBefore(jobPosting.getCloseDate()))
                lastApplicationClosed = jobPosting.getCloseDate();
        }
    }

    void removeInProgress(JobPosting job){
        applicationsInProgress.remove(job);
    }
}
