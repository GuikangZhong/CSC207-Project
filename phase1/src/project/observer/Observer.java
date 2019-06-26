package project.observer;

import project.application.Application;
import project.application.Job;
import project.interview.InterviewRecord;
import project.user.Applicant;
import project.user.HR;

import java.util.List;

public interface Observer {
    // TODO: tailor these methods according to your need
    void updateOnHireResult(Applicant applicant, Job job);
    void updateOnOneInterviewFinished(InterviewRecord record);
    void udpateOnRecommendationListRecieved(HR hr, List<InterviewRecord> recommendationList);
    // void updateOnXXX

}
