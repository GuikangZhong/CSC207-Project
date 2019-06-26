package project.interview;

import project.application.Job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InterviewProgress {
    private List<Interview> interviews;
    private List<InterviewRecord> interviewees;

    public List<Interview> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public List<InterviewRecord> getInterviewees() {
        return Collections.unmodifiableList(interviewees);
    }

    public Job getJob() {
        return job;
    }

    private Job job;
    private Iterator<Interview> interviewIterator;
    private Interview currentInterview;

    // interview is from InterviewBuilder.getInterviews()
    private InterviewProgress(Job job, List<Interview> interviews, List<InterviewRecord> interviewees) {
        this.job = job;
        this.interviews = interviews;
        this.interviewees = interviewees;
        interviewIterator = interviews.iterator();
        currentInterview = interviewIterator.next();
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
        // TODO: do we need to notify those not passed?
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
        assert !isLastRound() && hasCurrentRoundFinished();
        currentInterview = interviewIterator.next();
        filterPassed();

    }
}
