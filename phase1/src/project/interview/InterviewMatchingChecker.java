package project.interview;

import project.user.Applicant;
import project.user.Interviewer;

@FunctionalInterface
public interface InterviewMatchingChecker {
    boolean isPossibleMatching(Interviewer interviewer, Applicant applicant, Interview interview);
}
