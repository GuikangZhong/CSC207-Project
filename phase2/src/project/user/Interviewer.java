package project.user;

import project.application.Company;
import project.interview.Interview;
import project.interview.InterviewGroup;
import project.observer.InterviewGroupObserver;
import project.observer.InterviewObserver;
import project.utils.Logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Interviewer extends User implements InterviewGroupObserver, InterviewObserver {
    private static final long serialVersionUID = 6252452179878258209L;
    static private Logger logger = Logging.getLogger();
    private List<InterviewGroup> interviews;

    public Interviewer(UserHistory history,
                       String username,
                       String password,
                       String realName,
                       List<Company> company) {
        super(history, username, password, realName, company);
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

    @Override
    public void updateOnGroupSubmitted(InterviewGroup group) {
        logger.info("Removed " + group + "on submitted");
        interviews.remove(group);
    }

    @Override
    public void updateOnInterviewRoundFinished(Interview interview) {

    }

    @Override
    public void updateOnHireResult(Interview interview) {
        if (interviews.removeIf(group -> group.getJobPosting() == interview.getJobPosting())) {
            logger.info("Removed interview " + interview);
        }
    }

    @Override
    public void updateOnNoMoreRounds(Interview interview) {

    }
}
