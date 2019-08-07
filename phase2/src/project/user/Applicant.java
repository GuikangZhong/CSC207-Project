package project.user;

import project.application.*;
import project.interview.Round;
import project.observer.*;
import project.utils.Logging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

//they passed the interview of not.
public class Applicant
        extends User implements Serializable,
        SystemObserver {
    private static final long serialVersionUID = 6591554659403402970L;
    private static int DocumentsAutoDeleteDays = 30;
    private static Logger logger = Logging.getLogger();
    private Collection<Application> applications;
    private List<Document> documents;
    private List<ApplicantObserver> observers;
    private ApplicantHistory history;

    public Applicant(ApplicantHistory history,
                     String username,
                     String password,
                     String realName,
                     List<Company> company) {
        super(username, password, realName, company);
        this.history = history;
        applications = new ArrayList<>();
        documents = new ArrayList<>();
        observers = new ArrayList<>();
    }

    private static int getDocumentsAutoDeleteDays() {
        return DocumentsAutoDeleteDays;
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

    public ApplicantHistory getApplicantHistory() {
        return history;
    }

    public List<Document> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    public Optional<Application> getApplicationOf(String jobTitle) {
        return getApplications().stream().filter(application -> application.getJobPosting().getJobTitle().equals(jobTitle)).findAny();
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public void removeDocument(int index) {
        documents.remove(index);
    }

    public boolean addDocument(Document newDocument) {
        if (documents.stream()
                .filter(document -> document.getDocumentType().equals(newDocument.getDocumentType()))
                .count() + 1
                <= newDocument.maxNumber()) {
            documents.add(newDocument);
            return true;
        }
        return false;
    }

    // tries to apply for a job
    public Application apply(JobPosting jobPosting) {
        Application application = new Application(this, getDocuments(), jobPosting);
        if (jobPosting.addApplication(application)) {
            jobPosting.addObserver(this.getApplicantHistory());
            applications.add(application);
            getApplicantHistory().addJobApplying(jobPosting);
            return application;
        }
        return null;
    }

    /**
     *
     * @param jobPosting: The job posting the applicant is withdrawing from
     * @param application: The application the applicant associated with the job posting.
     * @return: true if and only if the withdrawal has been successful.
     */

    public boolean withdraw(JobPosting jobPosting, Application application) {
        // you can't withdraw if someone is hired for the job posting
        if (jobPosting.getStatus() == JobPosting.Status.FILLED || jobPosting.getStatus() == JobPosting.Status.UNFILLED){
            return false;
        }
        moveToApplied(jobPosting);
        applications.remove(application);
        jobPosting.removeApplication(application);
        for (ApplicantObserver observer : observers) {
            observer.updateOnApplicationWithdraw(application);
        }
        jobPosting.removeObserver(this.getApplicantHistory());
        return true;
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

    /**
     * Move the job from jobApplying to jobApplied.
     * @param job: The job being moved.
     */

    public void moveToApplied(JobPosting job) {
        ApplicantHistory history = getApplicantHistory();
        history.removeJobApplying(job);
        history.addJobApplied(job);
    }

    public void addHired(JobPosting job){
        ApplicantHistory history = getApplicantHistory();
        moveToApplied(job);
        history.addHiredPositions(job);
//        history.removeInProgress(job);
    }

    public void updateInterviewProgress(JobPosting job, Round round){
        ApplicantHistory history = getApplicantHistory();
        history.addApplicationInProgress(job, round);
    }

    public void addRejected(JobPosting job){
        ApplicantHistory history = getApplicantHistory();
        history.addApplicationRejected(job);
        history.removeInProgress(job);
    }

}
