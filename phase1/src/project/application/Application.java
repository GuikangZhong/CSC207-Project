package project.application;

import project.interview.InterviewProgress;
import project.user.Applicant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application implements Serializable {
    private static final long serialVersionUID = -8550792289386170705L;
    private Applicant applicant;
    private List<Document> documents;
    private Job job;
    private InterviewProgress progress;

    public Application(Applicant applicant, List<Document> documents, Job job) {
        this.applicant = applicant;
        this.documents = new ArrayList<>(documents);
        this.job = job;
        this.progress = null;
    }

    public InterviewProgress getProgress() {
        return progress;
    }

    public void setProgress(InterviewProgress progress) {
        this.progress = progress;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public List<Document> getDocument() {
        return Collections.unmodifiableList(documents);
    }

    public Job getJob() {
        return job;
    }
}
