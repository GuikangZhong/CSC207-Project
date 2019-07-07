package project.user;

import project.application.Company;
import project.interview.InterviewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Interviewer extends User {
    private static final long serialVersionUID = 6252452179878258209L;

    private List<InterviewGroup> interviews;

    public Interviewer(UserHistory history,
       String username,
       String password,
       String realName,
       String company) {
        super(history, username, password, realName, company);
    }

    @Override
    public final Type getType() {
        return Type.INTERVIEWER;
    }

    public List<InterviewGroup> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public void setInterviews(List<InterviewGroup> interviews) {
        this.interviews = interviews;
    }
}
