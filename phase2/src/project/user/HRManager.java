package project.user;

import project.application.Company;
import project.application.Job;
import project.application.JobPosting;
import project.interview.InPersonRound;
import project.interview.Interview;
import project.interview.InterviewSetup;
import project.interview.PhoneRound;
import project.observer.InterviewObserver;
import project.observer.JobPostingObserver;
import project.system.MainSystem;

import java.util.Collection;
import java.util.Optional;

public class HRManager extends UserManager<HR> implements InterviewObserver, JobPostingObserver {
    private static final long serialVersionUID = 5741768326107391635L;

    private Company company;

    public HRManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
    }


    /**
     * Enable HR Manager to schedule future interview when the job posting has been closed.
     * @param jobPosting: The closed job posting.
     */
    @Override
    public void updateOnJobPostingClosure(JobPosting jobPosting) {
        // select an HR
        HR hr = jobPosting.getHr();

        InterviewSetup setup = new InterviewSetup();
        Job job = jobPosting.getJob();
        setup.addRound(new PhoneRound());
        setup.addRound(new InPersonRound());
        setup.addRound(new InPersonRound());
        setup.addRound(new InPersonRound());
        Interview interview = new Interview(hr, jobPosting, setup);
        interview.addObserver(this);
        interview.addObserver(company.getJobPostingManager());
        hr.addInterviewsToBeScheduled(interview);

    }

    @Override
    public void updateOnInterviewRoundFinished(Interview interview) {
        HR hr = interview.getHR();
        hr.addInterviewRoundFinished(interview);
    }

    @Override
    public void updateOnHireResult(Interview interview) {

    }

    @Override
    public void updateOnNoMoreRounds(Interview interview) {
        HR hr = interview.getHR();
        hr.addFinalCandidates(interview);
    }
}
