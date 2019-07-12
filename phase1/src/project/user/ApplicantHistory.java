package project.user;

import project.application.Job;
import project.application.JobPosting;
import project.observer.JobPostingObserver;
import java.time.LocalDateTime;
import java.util.*;

public class ApplicantHistory extends UserHistory implements JobPostingObserver {
    private static final long serialVersionUID = -3949731953506050255L;

    private List<Job> jobApplied;
    private LocalDateTime lastApplicationClosed;
    private LocalDateTime closedDateBeforeWithdrawal;
    private HashMap<Job, Boolean> jobApplying;

    public ApplicantHistory(LocalDateTime now) {
        super(now);
        jobApplied = new ArrayList<>();
        jobApplying = new HashMap<>();
        lastApplicationClosed = null;
        closedDateBeforeWithdrawal = null;
    }

    public List<Job> getJobApplied() {
        return Collections.unmodifiableList(jobApplied);
    }

    public Set<Job> getJobApplying() {
        return Collections.unmodifiableSet(jobApplying.keySet());
    }

    public LocalDateTime getLastApplicationClosed() {
        return lastApplicationClosed;
    }

    void addJobApplied(Job job) {
        if(!jobApplied.contains(job))
            jobApplied.add(job);
    }

    void addJobApplying(Job job) {
        jobApplied.remove(job);
        jobApplying.put(job, false);
        closedDateBeforeWithdrawal = lastApplicationClosed;
        lastApplicationClosed = null;
    }

    void removeJobApplying(Job job) {
//        jobApplying.removeIf(e -> e.getTitle().equals(name));
        jobApplying.remove(job);
        if (allApplicationClosed() && lastApplicationClosed == null){
            lastApplicationClosed = closedDateBeforeWithdrawal;
            closedDateBeforeWithdrawal = null;
        }
    }

    private boolean allApplicationClosed(){
        return (!(jobApplying.values().contains(false)));
    }

    @Override
    public void updateOnJobPostingClosure(JobPosting jobPosting) {
//        lastApplicationClosed = jobPosting.getCloseDate();
        jobApplying.put(jobPosting.getJob(), true);
        if (allApplicationClosed()){
            lastApplicationClosed = jobPosting.getCloseDate();
        }
    }
}
