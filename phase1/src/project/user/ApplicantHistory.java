package project.user;

import project.application.Job;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class ApplicantHistory extends UserHistory{
    private List<Job> jobApplied;

    // naming...
    private List<Job> jobApplying;

    public List<Job> getJobApplied() {
        return Collections.unmodifiableList(jobApplied);
    }

    public List<Job> getJobApplying() {
        return Collections.unmodifiableList(jobApplying);
    }

    public LocalDateTime getLastApplicationClosed() {
        return lastApplicationClosed;
    }

    private LocalDateTime lastApplicationClosed;
    // TODO: add whatever argument you want
    public ApplicantHistory(Clock clock){
        super(clock);
    }

}
