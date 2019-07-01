package project.interview;

import project.user.HR;
import project.user.Interviewer;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class InterviewAssignment implements Serializable {
    private static final long serialVersionUID = -1255669893637911601L;
    private Interviewer interviewer;
    private List<InterviewRecord> interviewees;
    private InterviewProgress interviewProgress;

    public InterviewAssignment(InterviewProgress interviewProgress,
                               Interviewer interviewer, List<InterviewRecord> interviewees) {
        this.interviewees = interviewees;
        this.interviewProgress = interviewProgress;
        this.interviewer = interviewer;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public List<InterviewRecord> getInterviewees() {
        return Collections.unmodifiableList(interviewees);
    }

    void submit(HR hr) {
        for (InterviewRecord record : interviewees) {
            record.setCurrentRoundFinished(true);
        }
        // notify InterviewAssignment
        interviewProgress.updateOnInterviewResult(hr);
    }
}
