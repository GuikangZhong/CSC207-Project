package project.user;

import project.application.Application;
import project.application.JobPosting;
import project.observer.SystemObserver;
import project.system.MainSystem;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ApplicantManager extends UserManager<Applicant> implements SystemObserver, Serializable {
    private static final long serialVersionUID = -1825044094227184815L;

    public ApplicantManager(MainSystem system) {
        super(system);
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        // move the applying job to applied job after the posting closed
        List<JobPosting> jobPostingList = getSystem().getAllJobPostings();
        for (JobPosting jobPosting: jobPostingList) {
            for (Application application: jobPosting.getApplications()){
                Applicant applicant = application.getApplicant();
                if (now.isAfter(jobPosting.getCloseDate())){
                    applicant.moveToApplied(jobPosting.getJob());
                }
            }
        }
    }
}
