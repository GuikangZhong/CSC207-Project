package project.observer;

import project.interview.InterviewAssignment;

import java.util.List;

public interface InterviewAssignedObserver {
    void updateOnInterviewAssigned(List<InterviewAssignment> assignments);
}
