package project.user;

import project.application.Company;
import project.application.Job;
import project.application.JobPosting;
import project.interview.Interview;
import project.observer.InterviewObserver;
import project.observer.JobPostingObserver;
import project.system.MainSystem;
import project.utils.Logging;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class HRManager extends UserManager<HR> implements InterviewObserver, JobPostingObserver {
    private static final long serialVersionUID = 5741768326107391635L;

    private Company company;
    private HRSelectionStrategy selectionStrategy;
    class DefaultSelectionStrategy implements HRSelectionStrategy{

        private static final long serialVersionUID = 4067923033790808132L;

        @Override
        public Optional<HR> select(String jobTitle, Collection<HR> hrList) {
            JobPosting jobPosting = company.getJobPostingManager().getJobPosting(jobTitle);
            return Optional.of((HR)company.getUser(jobPosting.getHrName()));
        }
    }

    public HRManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
        useSelectionStrategy(new DefaultSelectionStrategy());
    }

    public void useSelectionStrategy(HRSelectionStrategy strategy) {
        selectionStrategy = strategy;
    }

    @Override
    public void updateOnJobPostingClosure(String jobTitle) {
        // select an HR
        Optional<HR> hr = selectionStrategy.select(jobTitle, users.values());
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
