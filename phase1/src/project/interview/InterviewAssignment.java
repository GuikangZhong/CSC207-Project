package project.interview;

import project.observer.InterviewResultObserver;
import project.user.Interviewer;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class InterviewAssignment {
    private Interviewer interviewer;
    private List<InterviewRecord> interviewees;

    public InterviewAssignment(Interviewer interviewer, List<InterviewRecord> interviewees){
        this.interviewees = interviewees;
        this.interviewer = interviewer;
    }
    public Interviewer getInterviewer() {
        return interviewer;
    }

    public List<InterviewRecord> getInterviewees() {
        return Collections.unmodifiableList(interviewees);
    }

    void submit(List<InterviewRecord> passed, List<InterviewRecord> failed) {
        for (InterviewRecord interviewee:passed) {
            interviewee.setPassed(true);
            IndividualInterviewProgress progress = interviewee.getApplication().getProgress();
            progress.setCurrentRoundFinished();
        }
        for (InterviewRecord interviewee:failed) {
            interviewee.setPassed(false);
            IndividualInterviewProgress progress = interviewee.getApplication().getProgress();
            progress.setCurrentRoundFinished();
        }
        // TODO: submit the result and notify whoever needed
        // TODO: hint: what about IndividualInterviewProgress, do they need to be notified?
    }


}
