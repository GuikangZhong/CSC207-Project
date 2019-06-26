package project.observer;

import project.application.Job;
import project.user.Applicant;

public interface HireResultObserver {
    void updateOnHireResult(Applicant applicant, Job job);
}
