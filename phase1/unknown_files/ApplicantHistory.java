package project;

import java.time.Clock;
import java.util.List;

public class ApplicantHistory extends UserHistory{
    private List<Job> appliedJobs;

    // naming...
    private Job currentlyApplyingJob;
    public ApplicantHistory(Clock clock){
        super(clock);
    }
}
