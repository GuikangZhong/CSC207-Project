package project.user;

import project.application.Company;
import project.observer.SystemTimeUpdateObserver;
import project.system.MainSystem;
import project.system.SystemClock;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class ApplicantManager extends UserManager<Applicant> implements SystemTimeUpdateObserver, Serializable {
    private static final long serialVersionUID = -1825044094227184815L;

    @Override
    public Applicant createUser(String name, String password) {

        return new Applicant(getSystem().getClock(), new ApplicantHistory(getSystem().getClock()), name, password);
    }

    public ApplicantManager(MainSystem system) {
        super(system);
    }

    void checkExpiredDocument(LocalDateTime now) {
        for(Applicant applicant : users.values()){
            LocalDateTime last = applicant.getHistory().getLastApplicationClosed();
            LocalDateTime deleteTime = last.plusDays(Applicant.getDocumentsAutoDeleteDays());
            if(deleteTime.isAfter(now)){
                // TODO:
                throw new RuntimeException("You should implement this!!!");
            }
        }
    }

    @Override
    public void updateOnTime(LocalDateTime now) {
        checkExpiredDocument(now);
    }
}
