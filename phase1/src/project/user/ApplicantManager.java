package project.user;

import project.application.Company;
import project.observer.SystemTimeUpdateObserver;
import project.system.MainSystem;
import project.system.SystemClock;

import java.time.Duration;
import java.time.LocalDateTime;

public class ApplicantManager extends UserManager<Applicant> implements SystemTimeUpdateObserver {
    public ApplicantManager(MainSystem system, Company company) {
        super(system);
    }

    @Override
    Applicant createUser(String name, String password) {

        return new Applicant(getSystem().getClock(), new ApplicantHistory(getSystem().getClock().getClock()), name, password);
    }

    public ApplicantManager(MainSystem system) {
        super(system);
    }

    void checkExpiredDocument(LocalDateTime now) {
        for (Applicant applicant : users.values()) {
            LocalDateTime last = applicant.getHistory().getLastApplicationClosed();
            LocalDateTime deleteTime = last.plusDays(Applicant.getDocumentsAutoDeleteDays());
            if (applicant.checkIfExpired()) {
                applicant.removeAllDoc();
            }
        }
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        checkExpiredDocument(now);
    }
}
