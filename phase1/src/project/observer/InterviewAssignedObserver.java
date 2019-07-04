package project.observer;

import project.interview.InterviewGroup;

import java.util.List;

public interface InterviewAssignedObserver {
    void updateOnInterviewAssigned(List<InterviewGroup> assignments);
}
