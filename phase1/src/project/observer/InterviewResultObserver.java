package project.observer;

import project.interview.InterviewRecord;

import java.util.List;

public interface InterviewResultObserver {
    void updateOnInterviewResult(List<InterviewRecord> passed, List<InterviewRecord> failed);
}
