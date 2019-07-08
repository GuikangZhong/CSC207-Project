package project.user;

import project.application.Company;
import project.interview.InterviewGroup;
import project.interview.InterviewGroupAssignmentStrategy;
import project.observer.InterviewGroupObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Interviewer extends User implements InterviewGroupObserver {
    private static final long serialVersionUID = 6252452179878258209L;

    private List<InterviewGroup> interviews;

    public Interviewer(UserHistory history,
       String username,
       String password,
       String realName,
       String company) {
        super(history, username, password, realName, company);
        interviews = new ArrayList<>();
    }

    @Override
    public final Type getType() {
        return Type.INTERVIEWER;
    }

    public List<InterviewGroup> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public void addInterviewGroup(InterviewGroup group){
        interviews.add(group);
    }
    public void removeInterviewGroup(InterviewGroup group){
        interviews.remove(group);
    }

    @Override
    public void updateOnGroupSubmitted(InterviewGroup group) {
        interviews.remove(group);
    }
}
