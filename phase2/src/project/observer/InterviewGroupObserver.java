package project.observer;

import project.interview.InterviewGroup;

public interface InterviewGroupObserver {
    /**
     * is called when group submits
     * @param group
     */
    void updateOnGroupSubmitted(InterviewGroup group);
}
