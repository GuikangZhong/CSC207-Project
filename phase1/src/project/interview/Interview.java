package project.interview;

import project.user.Interviewer;

import java.io.Serializable;

public class Interview implements Serializable {
    private Interviewer interviewer;
    private final String interviewType;

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public int getMaxInterview() {
        return maxInterview;
    }

    public Round getRound() {
        return round;
    }

    private Round round;
    private final int maxInterview;

    Interview(String type, Round round, int maxInterview){
        interviewType = type;
        this.maxInterview = maxInterview;
        this.round = round;
    }
}
