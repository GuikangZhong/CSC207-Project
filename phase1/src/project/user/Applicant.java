package project.user;

import project.application.Application;
import project.application.Document;
import project.application.Job;
import java.util.*;

// TODO: When InterviewAssignment.submit is called, applicants will be notified whether they passed the interview of not.
public class Applicant extends User<ApplicantHistory> {
    private Collection<Application> applications;
    private List<Document> documents;

    Applicant(ApplicantHistory history, String username, String password) {
        super(history, username, password);
        applications = new ArrayList<>();
        documents = new ArrayList<>();
    }

    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    void removeDocument(int index) {
        documents.remove(index);
    }

    boolean checkIfExpired(){
        return false;
    }

    void removeIfExpired(){
        // TODO:
    }

    void addDocument(Document document) {
        documents.add(document);
    }

    void updateDocument(int index, Document document) {
        documents.set(index, document);
    }

    Application createApplication(Job job) {
        Application application = new Application(this, getDocuments(), job);
        applications.add(application);
        getHistory().addJobApplying(job);
        return application;
    }

    void withdraw(String jobName) {
        for (Application application: applications){
            if (application.getJob().getTitle().equals(jobName)){
                applications.remove(application);
                this.getHistory().removeJobApplying(jobName);
            }
        }
    }

    @Override
    public Type getType(){return Type.APPLICANT;}
}
