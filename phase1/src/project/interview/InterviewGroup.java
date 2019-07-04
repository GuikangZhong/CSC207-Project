package project.interview;

import java.util.Map;

public class InterviewGroup {
    private String interviewer;
    private Map<String, Boolean> applicantsStatus;
    private Interview interview;

    public String getInterviewer() {
        return interviewer;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    private boolean submitted = false;

    public void submit(){
        submitted = true;
    }
}
