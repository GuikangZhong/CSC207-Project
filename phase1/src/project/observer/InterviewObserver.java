package project.observer;

import project.interview.Interview;

public interface InterviewObserver {
    void updateOnInterviewRoundFinished(Interview interview);
    void updateOnHireResult(Interview interview);
    void updateOnNoMoreRounds(Interview interview);
}
