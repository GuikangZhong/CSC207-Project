package project.user;

import project.application.*;
import project.system.SystemClock;

import java.util.*;

// TODO: When InterviewAssignment.submit is called, applicants will be notified whether 
//they passed the interview of not.
public class Applicant extends User<ApplicantHistory> {
    private Collection<Application> applications;
    private List<Document> documents;

    Applicant(ApplicantHistory history, String username, String password, SystemClock clock) {
        super(history, username, password, clock);
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

    void addDocument(Document document) {
    	documents.add(document);
    }
    
    void updateDocument(int index, Document document) {
    	documents.set(index, document);
    }

    
    
    boolean checkIfExpired(){
        return false;
    }

    void removeIfExpired(){
        // TODO:
    }

    // tries to apply for a job
    // throws RuntimeException if requirement not met
    Application apply(JobPosting jobPosting) {
        Application application = new Application(this, getDocuments(), jobPosting.getJob());
        jobPosting.addApplication(application);
        applications.add(application);
        getHistory().addJobApplying(jobPosting.getJob());
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
    public Type getType(){
    	return Type.APPLICANT;
    }
}
