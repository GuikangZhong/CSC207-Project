package project.interview;

import project.user.Interviewer;

import java.util.List;

public interface InterviewMatchingStrategy {
    // TODO： don't call Interview.setAssignments
    List<InterviewAssignment> match(List<Interviewer> interviewers,
                                    List<InterviewRecord> interviewees,
                                    Interview interview);
}