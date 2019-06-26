package project.application;

import project.user.Applicant;

import java.util.Collections;
import java.util.List;

public class Application {
    private Applicant applicant;
    private List<Document> documents;
    private Job job;

    public Application(Applicant applicant, List<Document> documents, Job job){
        this.applicant = applicant;
        this.documents = documents;
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
