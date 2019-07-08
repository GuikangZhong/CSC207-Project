package project.observer;

import project.interview.InterviewGroup;

public interface InterviewGroupObserver {
    void updateOnGroupAssigned(InterviewGroup group);
    void updateOnGroupSubmitted(InterviewGroup group);
}
