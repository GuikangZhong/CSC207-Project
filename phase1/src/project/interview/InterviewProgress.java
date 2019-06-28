package project.interview;

import project.application.Application;
import project.application.Job;
import project.user.Applicant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InterviewProgress {
    private List<Interview> interviews;
    private List<InterviewRecord> interviewees;
    private Job job;
    private Iterator<Interview> interviewIterator;
    private Interview currentInterview;

    private InterviewProgress(Job job, List<Interview> interviews, List<InterviewRecord> interviewees) {
        this.job = job;
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

    public Job getJob() {
        return job;
    }

    // interview is from InterviewBuilder.getInterviews()

    static InterviewProgress createInterviewProgress(Job job,
                                                     InterviewBuilder builder,
                                                     List<Application> applications) {
        List<InterviewRecord> interviewees = new ArrayList<>();
        for (Application application : applications) {
            interviewees.add(new InterviewRecord(application));
        }
        return new InterviewProgress(job, builder.getInterviews(), interviewees);
    }

    public boolean hasCurrentRoundFinished() {
        for (InterviewRecord record : interviewees) {
            IndividualInterviewProgress individualInterviewProgress = record.getApplication().getProgress();
            if (!individualInterviewProgress.isCurrentRoundFinished())
                return false;
        }
        return true;
    }

    public boolean isLastRound() {
        return !interviewIterator.hasNext();
    }

    void filterPassed() {
        // Notification done in InterviewAssignment.
        List<InterviewRecord> passed = new ArrayList<>();
        for (InterviewRecord record : interviewees) {
            if (record.isPassed()) {
                passed.add(record);
            }
        }
        for (InterviewRecord record : passed) {
            record.setPassed(false);
        }
        interviewees = passed;
    }

    public void toNextRound() {
        if (!isLastRound() && hasCurrentRoundFinished()){
        currentInterview = interviewIterator.next();
        filterPassed();}
    }

    public List<InterviewRecord> getRecommendationList() {
        List<InterviewRecord> recommendation = new ArrayList<>();
        for (InterviewRecord interviewee:interviewees
             ) {if (interviewees.size()>1 && isLastRound() && hasCurrentRoundFinished()){
            recommendation.add(interviewee); }
        }
        return recommendation;
    }
}
