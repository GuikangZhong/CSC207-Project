package project.interview;

import project.application.Job;

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
    private Iterator<Interview> currentInterview;

    public InterviewProgress(Job job, List<Interview> interviews,List<InterviewRecord>interviewees){
        this.job = job;
        this.interviews = interviews;
        this.interviewees = interviewees;
        currentInterview = interviews.iterator();

    }
}
