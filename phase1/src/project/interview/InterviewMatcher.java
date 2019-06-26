package project.interview;

import project.user.Interviewer;

import java.util.List;

public interface InterviewMatcher {
    // TODO： don't call Interview.setAssingments
    List<InterviewAssignment> match(List<Interviewer> interviewers,
                                    List<InterviewRecord> interviewees,
                                    Interview interview);
}
