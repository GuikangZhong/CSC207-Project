package project.application;

import project.interview.IndividualInterviewProgress;
import project.user.Applicant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    private Applicant applicant;
    private List<Document> documents;
    private Job job;

    public IndividualInterviewProgress getProgress() {
        return progress;
    }

    public void setProgress(IndividualInterviewProgress progress) {
        this.progress = progress;
    }

    private IndividualInterviewProgress progress = null;

    public Application(Applicant applicant, List<Document> documents, Job job){
        this.applicant = applicant;
        this.documents = new ArrayList<>(documents);
        this.job = job;
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
