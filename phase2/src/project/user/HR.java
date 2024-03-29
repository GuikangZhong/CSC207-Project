package project.user;

import project.application.Company;
import project.application.JobPosting;
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
    private List<JobPosting> postingsToAssignFormat;
    static private Logger logger = Logging.getLogger();

    public HR(
              String username,
              String password,
              String realName,
              List<Company> company) {
        super(username, password, realName, company);
        finalCandidates = new HashMap<>();
        interviewsRoundFinished = new ArrayList<>();
        interviewsToBeScheduled = new ArrayList<>();
        postingsToAssignFormat = new ArrayList<>();
    }

    public List<Interview> getInterviewsRoundFinished() {
        return interviewsRoundFinished;
    }


    public List<Interview> getInterviewsToBeScheduled() {
        return interviewsToBeScheduled;
    }

    public List<JobPosting> getPostingsToAssignFormat() {
        return postingsToAssignFormat;
    }

    public void addPostingToAssignFormat(JobPosting posting){
        if (!(postingsToAssignFormat.contains(posting))){
            postingsToAssignFormat.add(posting);
        }
    }

    public void postingAssignedFormat(JobPosting posting){
        postingsToAssignFormat.remove(posting);
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
   public  void addFinalCandidates(Interview interview) {
        finalCandidates.put(interview.getJobPosting().getJobTitle(), interview);
    }

   public  void addInterviewRoundFinished(Interview interview) {
        interviewsRoundFinished.add(interview);
    }

   public  void addInterviewsToBeScheduled(Interview interview) {
        logger.info("Added " + interview.getJobPosting().getJobTitle() + " for " + getUsername());
        interviewsToBeScheduled.add(interview);
    }
}
