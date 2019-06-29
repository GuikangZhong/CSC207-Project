package project.observer;

import project.interview.InterviewProgress;

public interface InterviewRoundFinishedObserver {
    void updateOnInterviewRoundFinished(InterviewProgress progress);
}
