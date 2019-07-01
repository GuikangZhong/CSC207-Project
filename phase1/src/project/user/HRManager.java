package project.user;

import project.application.Company;
import project.interview.InterviewProgress;
import project.observer.InterviewRoundFinishedObserver;
import project.observer.JobPostingClosureObserver;
import project.system.MainSystem;

import java.util.Optional;

public class HRManager
        extends UserManager<HR>
        implements InterviewRoundFinishedObserver, JobPostingClosureObserver {
    private Company company;
    private HRSelectionStrategy selectionStrategy;

    public void useSelectionStrategy(HRSelectionStrategy strategy) {
        selectionStrategy = strategy;
    }

    @Override
    public void updateOnJobPostingClosure(String jobTitle) {
        // select an HR
        Optional<HR> hr = selectionStrategy.select(users.values());
        if (hr.isPresent()) {
            hr.get().addInterviewsToBeScheduled(jobTitle);
        } else {
            throw new RuntimeException("No HR !!!!!");
        }
    }

    public HRManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
    }

    @Override
    HR createUser(String name, String password) {
        return new HR(new UserHistory(getSystem().getClock().getClock()), name, password, company);
    }

    @Override
    public void updateOnInterviewRoundFinished(InterviewProgress progress) {
        HR hr = getUser(progress.getHRName());
        hr.addInterviewRoundFinished(progress.getJobPosting().getJobTitle());
    }
}
