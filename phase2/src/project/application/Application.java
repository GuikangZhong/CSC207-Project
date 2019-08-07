package project.application;

import project.interview.InterviewGroup;
import project.observer.InterviewGroupObserver;
import project.user.Applicant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Application is the material the applicant sends for the job posting
 */

public class Application implements Serializable, InterviewGroupObserver {
    private static final long serialVersionUID = -8550792289386170705L;
    private Applicant applicant;
    private List<Document> documents;
    private JobPosting jobPosting;
    private List<String> roundsFinished;

    public Application(Applicant applicant, List<Document> documents, JobPosting job) {
        this.applicant = applicant;
        this.documents = new ArrayList<>(documents);
        this.jobPosting = job;
        this.roundsFinished = new ArrayList<>();
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public List<Document> getDocument() {
        return Collections.unmodifiableList(documents);
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public String getRoundsFinishedInfo() {
        if (roundsFinished.size() == 0) {
            return "Submitted";
        } else {
            StringBuilder stringBuilder = new StringBuilder("Finished rounds:\n");
            for (String s : roundsFinished) {
                stringBuilder.append(s + "\n");
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public void updateOnGroupSubmitted(InterviewGroup group) {
        roundsFinished.add(group.getRound().toString());
        boolean passed = group.isApplicantPassed(applicant);
        if (!passed) {
            applicant.moveToApplied(getJobPosting());
            applicant.addRejected(getJobPosting());
        }
        else {
            applicant.updateInterviewProgress(getJobPosting(), group.getRound());
        }
    }
}
