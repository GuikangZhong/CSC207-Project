package project.user;

import project.application.Company;
import project.application.Job;
import project.interview.Interview;
import project.observer.InterviewObserver;
import project.observer.JobPostingObserver;
import project.system.MainSystem;

import java.util.List;
import java.util.Optional;

public class HRManager extends UserManager<HR> implements InterviewObserver, JobPostingObserver {
    private static final long serialVersionUID = 5741768326107391635L;

    private Company company;
    private HRSelectionStrategy selectionStrategy;

    public HRManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
    }

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

    @Override
    public void updateOnInterviewRoundFinished(Interview interview) {
        HR hr = getUser(interview.getHR());
        hr.addInterviewRoundFinished(interview.getJobPosting().getJobTitle());
    }

    @Override
    public void updateOnHireResult(List<Applicant> applicants, Job job) {
        // TODO:
    }
}
