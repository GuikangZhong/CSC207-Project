package project.observer;

import project.application.Job;
import project.application.JobPosting;
import project.interview.InterviewRecord;
import project.user.Applicant;

import java.util.List;

public interface HireResultObserverHR {
    void updateOnHireResult(List<InterviewRecord> passed, List<InterviewRecord> failed, JobPosting jobPosting);
}
