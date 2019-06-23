package project;

import project.user.Applicant;

public class Application {
    private Applicant applicant;
    private Document document;
    private Job job;
    private ApplicationStatus status;

    public Application(Applicant applicant, Document document, Job job){
        this.applicant = applicant;
        this.document = document;
        this.job = job;
        this.status = new ApplicationStatus();
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Document getDocument() {
        return document;
    }

    public Job getJob() {
        return job;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}
