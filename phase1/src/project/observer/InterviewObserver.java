package project.observer;

import project.application.Job;
import project.interview.Interview;
import project.user.Applicant;

import java.util.List;

public interface InterviewObserver {
    void updateOnInterviewRoundFinished(Interview interview);
    void updateOnHireResult(List<Applicant> applicants, Job job);
}
