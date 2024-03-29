package project.user;

import project.application.Company;
import project.interview.InterviewGroup;
import project.observer.InterviewGroupObserver;
import project.utils.Logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Interviewer extends User implements InterviewGroupObserver {
    private static final long serialVersionUID = 6252452179878258209L;
    static private Logger logger = Logging.getLogger();
    private List<InterviewGroup> interviews;

    public Interviewer(
                       String username,
                       String password,
                       String realName,
                       List<Company> company) {
        super(username, password, realName, company);
        interviews = new ArrayList<>();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public final Type getType() {
        return Type.INTERVIEWER;
    }

    public List<InterviewGroup> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public void addInterviewGroup(InterviewGroup group) {
        interviews.add(group);
    }

    public void removeInterviewGroup(InterviewGroup group){
        interviews.remove(group);
    }

    @Override
    public void updateOnGroupSubmitted(InterviewGroup group) {
        logger.info("Removed " + group + "on submitted");
        interviews.remove(group);
    }
}
