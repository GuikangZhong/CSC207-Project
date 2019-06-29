package project.observer;

import project.application.Job;
import project.user.Applicant;

import java.util.List;

public interface HireResultObserver {
    void updateOnHireResult(List<Applicant> applicant, Job job);
}
