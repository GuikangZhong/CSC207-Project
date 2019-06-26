package project.observer;

import project.interview.InterviewRecord;
import project.user.HR;

import java.util.List;

public interface Observer {
    // TODO: tailor these methods according to your need
    void updateOnOneInterviewFinished(InterviewRecord record);
    void udpateOnRecommendationListRecieved(HR hr, List<InterviewRecord> recommendationList);
    // void updateOnXXX

}
