package project.observer;

import project.interview.Interview;

public interface InterviewRoundFinishedObserver {
    void updateOnInterviewRoundFinished(Interview interview);
}
