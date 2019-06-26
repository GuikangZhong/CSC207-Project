package project.interview;

import project.user.Interviewer;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class InterviewAssignment extends Observable{
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

    void submit(List<InterviewRecord> passed, List<InterviewRecord> failed){
        for (InterviewRecord interviewee:passed) {
            interviewee.setPassed(true);
            IndividualInterviewProgress progress = interviewee.getApplication().getProgress();
            progress.setCurrentRoundFinished();
            setChanged();
            notifyObservers("Congratulations! You passed the interview. ");
        }
        for (InterviewRecord interviewee:failed) {
            interviewee.setPassed(false);
            IndividualInterviewProgress progress = interviewee.getApplication().getProgress();
            progress.setCurrentRoundFinished();
            setChanged();
            notifyObservers("We're sorry, you didn't pass the interview. ");
        }
        // TODO: submit the result and notify whoever needed
        // TODO: hint: what about IndividualInterviewProgress, do they need to be notified?
    }
}
