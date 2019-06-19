package project;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Applicant extends User {
    private Document document;
    private Collection<Application> applications;

    Applicant(UserHistory history, String username, String password) {
        super(history, username, password);
    }

    void applyFor(JobPosting jobPosting) {
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
}
