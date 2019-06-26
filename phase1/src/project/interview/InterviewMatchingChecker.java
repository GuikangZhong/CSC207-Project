package project.interview;

import project.user.Applicant;
import project.user.Interviewer;

@FunctionalInterface
public interface InterviewMatchingChecker {
    boolean isPossibleMatching(Interview interview, Interviewer interviewer, Applicant applicant);
}
