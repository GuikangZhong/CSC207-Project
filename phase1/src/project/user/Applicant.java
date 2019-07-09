package project.user;

import project.application.*;
import project.observer.*;
import project.system.SystemClock;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// TODO: When InterviewAssignment.submit is called, applicants will be notified whether 
//they passed the interview of not.
public class Applicant
        extends User implements Serializable,
        SystemObserver{
    private static final long serialVersionUID = 6591554659403402970L;

    private Collection<Application> applications;
    private List<Document> documents;
    private List<ApplicantObserver> observers;
    private static int DocumentsAutoDeleteDays = 30;
    private static Logger logger = Logging.getLogger();

    public Applicant(ApplicantHistory history,
                     String username,
                     String password,
                     String realName,
                     String company) {
        super(history, username, password, realName, company);
        applications = new ArrayList<>();
        documents = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addObserver(ApplicantObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ApplicantObserver observer) {
        if (!observers.remove(observer)) {
            throw new RuntimeException("You removed an observer that is not in the list");
        } else {
            logger.info("Applicant " + getUsername() + " Removed observer" + observer);
        }
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

    public Document getDocument(String name) {
        if (documents.size() != 0) {
            for (Document document : documents) {
                if (document.getName().equals(name)) {
                    return document;
                }
            }
        }
        return null;
    }

    public Optional<Application> getApplicationOf(String jobTitle) {
        return getApplications().stream().filter(application -> application.getJob().getTitle().equals(jobTitle)).findAny();
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void removeDocument(int index) {
        documents.remove(index);
    }

    public boolean addDocument(Document newDocument) {
        if (documents.stream()
                .filter(document -> document.type().equals(newDocument.type()))
                .count() + 1
                <= newDocument.maxNumber()) {
            documents.add(newDocument);
            return true;
        }
        return false;
    }

    // tries to apply for a job
    // throws RuntimeException if requirement not met
    public Application apply(JobPosting jobPosting) {
        Application application = new Application(this, getDocuments(), jobPosting.getJob());
        jobPosting.addObserver(this.getApplicantHistory());
        if (jobPosting.addApplication(application)) {
            applications.add(application);
            getApplicantHistory().addJobApplying(jobPosting.getJob());
            return application;
        }
        return null;
    }

    public void withdraw(JobPosting jobPosting, Application application) {
        moveToApplied(jobPosting.getJob());
//        this.getApplicantHistory().removeJobApplying(jobPosting.getJobTitle());
        applications.remove(application);
        jobPosting.removeApplication(application);
        for (ApplicantObserver observer : observers) {
            observer.updateOnApplicationWithdraw(application);
        }
        jobPosting.removeObserver(this.getApplicantHistory());
    }

    @Override
    public final Type getType() {
        return Type.APPLICANT;
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        return getUsername().equals(((Applicant) obj).getUsername());
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        ApplicantHistory history = getApplicantHistory();
        if (history != null) {
            LocalDateTime close = history.getLastApplicationClosed();
            if (close != null) {
                if (close.plusDays(getDocumentsAutoDeleteDays()).isBefore(now)) {
                    documents.removeIf(document -> document.getCreatedDate().isBefore(close));
                }
            }
        }
    }

    private void moveToApplied(Job job) {
        ApplicantHistory history = getApplicantHistory();
        history.removeJobApplying(job.getTitle());
        history.addJobApplied(job);

    }


    public void updateOnPassed(Job job) {
        moveToApplied(job);
    }


    public void updateOnFailed(Job job) {
        moveToApplied(job);
    }
}
