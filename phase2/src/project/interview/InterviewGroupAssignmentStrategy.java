package project.interview;

import project.user.Applicant;
import project.user.Interviewer;

import java.util.List;

public interface InterviewGroupAssignmentStrategy {
    List<InterviewGroup> select(List<Applicant> applicants, List<Interviewer> interviewers);
}
