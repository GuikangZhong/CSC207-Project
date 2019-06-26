package project.interview;

import project.user.Interviewer;

import java.util.Collections;
import java.util.List;

public class InterviewAssignment {
    private Interviewer interviewer;

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public List<InterviewRecord> getInterviewees() {
        return Collections.unmodifiableList(interviewees);
    }

    private List<InterviewRecord> interviewees;

    public InterviewAssignment(Interviewer interviewer, List<InterviewRecord> interviewees){
        this.interviewees = interviewees;
        this.interviewer = interviewer;
    }

    void submit(){
        // TODO: submit the result and notify whoever needed
        // TODO: hint: what about IndividualInterviewProgress, do they need to be notified?
    }
}
