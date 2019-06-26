package project.user;

import com.sun.istack.internal.NotNull;
import project.application.Application;
import project.application.Document;
import project.application.Job;
import project.application.JobPosting;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Applicant extends User<ApplicantHistory> {
    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    private List<Document> documents;

    public Collection<Application> getApplications() {
        return applications;
    }

    private Collection<Application> applications;

    Applicant(ApplicantHistory history, String username, String password) {
        super(history, username, password);
    }

    void removeDocument(int index) {
        documents.remove(index);
    }


    boolean checkIfExpired(){
        // TODO:
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
        return new Application(this, getDocuments(), job);
    }

    void withdraw(Application application) {
        // TODO:
    }

    @Override
    public Type getType(){return Type.APPLICANT;}
}
