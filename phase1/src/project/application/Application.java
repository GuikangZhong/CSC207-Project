package project.application;

import project.interview.ApplicationStatus;
import project.user.Applicant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Application is the material the applicant sends for the job posting
 */

public class Application implements Serializable {
    private static final long serialVersionUID = -8550792289386170705L;
    private Applicant applicant;
    private List<Document> documents;
    private Job job;
    private ApplicationStatus status;

    public Application(Applicant applicant, List<Document> documents, Job job) {
        this.applicant = applicant;
        this.documents = new ArrayList<>(documents);
        this.job = job;
        this.status = new ApplicationStatus();
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

    public ApplicationStatus getStatus() {
        return status;
    }
}
