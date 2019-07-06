package project.user;

import project.application.*;
import project.system.SystemClock;

import java.io.Serializable;
import java.util.*;

// TODO: When InterviewAssignment.submit is called, applicants will be notified whether 
//they passed the interview of not.
public class Applicant extends User implements Serializable {
    private static final long serialVersionUID = 6591554659403402970L;

    private Collection<Application> applications;
    private List<Document> documents;
    private static int DocumentsAutoDeleteDays = 30;

    public Applicant(ApplicantHistory history,
                     String username,
                     String password,
                     String realName,
                     String company) {
        super(history, username, password, realName, company);
        applications = new ArrayList<>();
        documents = new ArrayList<>();
    }

    public static int getDocumentsAutoDeleteDays() {
        return DocumentsAutoDeleteDays;
    }


    public ApplicantHistory getApplicantHistory() {
        return (ApplicantHistory) getHistory();
    }

    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void removeDocument(int index) {
        documents.remove(index);
    }

    public void addDocument(Document newDocument) {
        boolean added = false;
        if (documents.size() == 0) {
            documents.add(newDocument);
        }
        else {
            int index = 0;
            for (Document document: documents){
                if (document.getType() == newDocument.getType()){
                    documents.set(index, newDocument);
                    added = true;
                }
                index ++;
            }
            if (added == false){
                documents.add(newDocument);
            }
        }
    }

    public boolean checkIfExpired() {
        return false;
    }

    public void removeIfExpired() {
        // TODO:
    }

    // tries to apply for a job
    // throws RuntimeException if requirement not met
    public Application apply(JobPosting jobPosting) {
        Application application = new Application(this, getDocuments(), jobPosting.getJob());
        jobPosting.addApplication(application);
        applications.add(application);
        getApplicantHistory().addJobApplying(jobPosting.getJob());
        return application;
    }

   public void withdraw(String jobName) {
        for (Application application : applications) {
            if (application.getJob().getTitle().equals(jobName)) {
                applications.remove(application);
                this.getApplicantHistory().removeJobApplying(jobName);
            }
        }
    }

    @Override
    public final Type getType() {
        return Type.APPLICANT;
    }

}
