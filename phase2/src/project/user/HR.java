package project.user;

import project.interview.*;
import project.utils.Logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class HR extends User {
    private static final long serialVersionUID = 6752053563376828029L;

    private Map<String, Interview> finalCandidates;
    private List<Interview> interviewsRoundFinished;
    private List<Interview> interviewsToBeScheduled;
    static private Logger logger = Logging.getLogger();

    public HR(UserHistory history,
              String username,
              String password,
              String realName,
              List<String> company) {
        super(history, username, password, realName, company);
        finalCandidates = new HashMap<>();
        interviewsRoundFinished = new ArrayList<>();
        interviewsToBeScheduled = new ArrayList<>();
    }

    public List<Interview> getInterviewsRoundFinished() {
        return interviewsRoundFinished;
    }


    public List<Interview> getInterviewsToBeScheduled() {
        return interviewsToBeScheduled;
    }

    @Override
    public final Type getType() {
        return Type.HR;
    }

    public Map<String, Interview> getFinalCandidates() {
        return finalCandidates;
    }


    /**
     * When the interview has ended whereas there are still multiple applicants, these applicants will be added to
     * finalCandidates.
     * @param interview: The ended interview where there are still multiple applicants remaining.
     */
    void addFinalCandidates(Interview interview) {
        finalCandidates.put(interview.getJob().getTitle(), interview);
    }

    void addInterviewRoundFinished(Interview interview) {
        interviewsRoundFinished.add(interview);
    }

    void addInterviewsToBeScheduled(Interview interview) {
        logger.info("Added " + interview.getJob().getTitle() + " for " + getUsername());
        interviewsToBeScheduled.add(interview);
    }
}
