package project.observer;

import project.interview.InterviewGroup;

public interface InterviewGroupObserver {
    void updateOnGroupSubmitted(InterviewGroup group);
}
