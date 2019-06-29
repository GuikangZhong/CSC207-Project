package project.interview;

import project.application.Application;
import project.application.Job;
import project.application.JobPosting;
import project.observer.InterviewResultObserver;
import project.observer.RecommendationNeededObserver;
import project.user.Applicant;
import project.user.HR;

import java.util.*;

public class InterviewProgress implements InterviewResultObserver {
    private List<Interview> interviews;
    private List<InterviewRecord> interviewees;
    private JobPosting jobPosting;
    private Iterator<Interview> interviewIterator;
    private Interview currentInterview;

    private InterviewProgress(JobPosting jobPosting, List<Interview> interviews, List<InterviewRecord> interviewees) {
        this.jobPosting = jobPosting;
        this.interviews = interviews;
        this.interviewees = interviewees;
        interviewIterator = interviews.iterator();
        currentInterview = interviewIterator.next();
    }

    public List<Interview> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public List<InterviewRecord> getInterviewees() {
        return Collections.unmodifiableList(interviewees);
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    // interview is from InterviewBuilder.getInterviews()

    static InterviewProgress createInterviewProgress(JobPosting jobposting,
                                                     InterviewBuilder builder,
                                                     List<Application> applications) {
        List<InterviewRecord> interviewees = new ArrayList<>();
        for (Application application : applications) {
            interviewees.add(new InterviewRecord(application));
        }
        return new InterviewProgress(jobposting, builder.getInterviews(), interviewees);
    }

    public boolean hasCurrentRoundFinished() {
        for (InterviewRecord record : interviewees) {
            if (!record.isCurrentRoundFinished())
                return false;
        }
        return true;
    }

    public boolean isLastRound() {
        return !interviewIterator.hasNext();
    }

    List<InterviewRecord> filterPassed() {
        // Notification done in InterviewAssignment.
        List<InterviewRecord> passed = new ArrayList<>();
        for (InterviewRecord record : interviewees) {
            if (record.isPassed()) {
                passed.add(record);
            }
        }
        return passed;
    }

    void resetPassed() {
        for (InterviewRecord record : interviewees) {
            record.setPassed(false);
        }
    }

    public void toNextRound() {
        assert (!isLastRound() && hasCurrentRoundFinished());
        Set<String> names = new HashSet<>();
        for (InterviewRecord record : filterPassed()) {
            if (record.isPassed()) {
                names.add(record.getApplication().getApplicant().getRealName());
            }
        }
        currentInterview.setNameApplicantPassed(names);
        currentInterview = interviewIterator.next();
        interviewees = filterPassed();
        resetPassed();
    }

    public List<InterviewRecord> getRecommendationList() {
        List<InterviewRecord> recommendation = new ArrayList<>();
        if (interviewees.size() > 1 && isLastRound() && hasCurrentRoundFinished()) {
            filterPassed();
            recommendation.addAll(interviewees);
        }
        return recommendation;
    }

    @Override
    public void updateOnInterviewResult() {
        if (hasCurrentRoundFinished()) {
            List<InterviewRecord> passed = filterPassed();
            if (passed.size() > jobPosting.getnApplicantNeeded() && !isLastRound()) {
                toNextRound();
                // TODO: should you notify HR too?
                // TODO: since HR might want to operate on subsequent interviews? (like assigning interviewers)
            } else if (passed.size() > jobPosting.getnApplicantNeeded()) {
                // TODO: recommendation list for HR
                // TODO: Don't observer the object itself
            } else {
                // hire
                List<Applicant> hired = new ArrayList<>();
                for (InterviewRecord record : passed) {
                    hired.add(record.getApplication().getApplicant());
                }

                // TODO: remove the call chain
                jobPosting.getCompany().updateOnHireResult(hired, jobPosting.getJob());
            }
        }
    }


}
