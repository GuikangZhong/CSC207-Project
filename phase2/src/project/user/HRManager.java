package project.user;

import project.application.JobPosting;
import project.interview.Interview;
import project.observer.InterviewObserver;
import project.observer.JobPostingObserver;
import project.system.MainSystem;

public class HRManager extends UserManager<HR> implements InterviewObserver, JobPostingObserver {
    private static final long serialVersionUID = 5741768326107391635L;


    public HRManager(MainSystem system) {
        super(system);
    }


    /**
     * Enable HR Manager to schedule future interview when the job posting has been closed.
     * @param jobPosting: The closed job posting.
     */
    @Override
    public void updateOnJobPostingClosure(JobPosting jobPosting){
        // select an HR
        HR hr = jobPosting.getHr();
        hr.addPostingToAssignFormat(jobPosting);
    }

    @Override
    public void updateOnInterviewRoundFinished(Interview interview) {
        HR hr = interview.getHR();
        hr.addInterviewRoundFinished(interview);
    }

    @Override
    public void updateOnNoMoreRounds(Interview interview) {
        HR hr = interview.getHR();
        hr.addFinalCandidates(interview);
    }
}
