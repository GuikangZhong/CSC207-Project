package project.observer;

import project.application.Job;
import project.user.Applicant;

public interface InterviewResultObserver {
    void updateOnPassed(Applicant applicant, Job job);

    void updateOnFailed(Applicant applicant, Job job);
}
