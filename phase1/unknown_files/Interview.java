package project;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Interview implements Serializable {
    public Collection<Interviewee> getInterviewees() {
        return interviewees;
    }

    private Collection<Interviewee> interviewees;
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

    public List<Interviewee> getInterviewersPassed() {
        return interviewees.stream().filter(interviewee -> interviewee.isPassed())
                .collect(Collectors.toList());
    }
}
