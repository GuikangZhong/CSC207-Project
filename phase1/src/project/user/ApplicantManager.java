package project.user;

import project.observer.SystemObserver;
import project.system.MainSystem;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ApplicantManager extends UserManager<Applicant> implements SystemObserver, Serializable {
    private static final long serialVersionUID = -1825044094227184815L;

    public ApplicantManager(MainSystem system) {
        super(system);
    }

    void checkExpiredDocument(LocalDateTime now) {
        for (Applicant applicant : users.values()) {
            LocalDateTime last = applicant.getApplicantHistory().getLastApplicationClosed();
            LocalDateTime deleteTime = last.plusDays(Applicant.getDocumentsAutoDeleteDays());
            if (deleteTime.isAfter(now)) {
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
