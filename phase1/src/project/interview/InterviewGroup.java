package project.interview;

import java.io.Serializable;
import java.util.Map;

public class InterviewGroup implements Serializable {
    private static final long serialVersionUID = 4355176965416536403L;
    private String interviewer;
    private Map<String, Boolean> applicantsStatus;
    private Interview interview;
    private Round round;

    public String getInterviewer() {
        return interviewer;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    private boolean submitted = false;

    public void submit(){
        submitted = true;
        round.updateOnGroupSubmitted();
    }
}
