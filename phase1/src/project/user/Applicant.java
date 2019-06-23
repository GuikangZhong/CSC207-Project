package project.user;

import com.sun.istack.internal.NotNull;
import project.application.Application;
import project.application.Document;
import project.application.JobPosting;

import java.util.Collection;
import java.util.Optional;

public class Applicant extends User<ApplicantHistory> {
    public Document getDocument() {
        return document;
    }

    private Document document;

    public Collection<Application> getApplications() {
        return applications;
    }

    private Collection<Application> applications;

    Applicant(ApplicantHistory history, String username, String password) {
        super(history, username, password);
    }

    void applyFor(@NotNull JobPosting jobPosting) {
        Application application = new Application(this, document, jobPosting.getJob());
        jobPosting.addApplication(application);
        applications.add(application);
    }

    void withdraw(JobPosting jobPosting) {
        Optional<Application> application =
                applications.stream()
                        .filter(app -> app.getApplicant() == this)
                        .findAny();
        if (application.isPresent()) {
            jobPosting.removeApplication(application.get());
            applications.remove(application.get());
        } else {
            throw new RuntimeException("You cannot withdraw an application you didn't apply for");
        }
    }

    void setDocument(Document document){
        this.document = document;
    }

}
