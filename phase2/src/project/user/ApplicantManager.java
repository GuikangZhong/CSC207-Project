package project.user;

import project.observer.SystemObserver;
import project.system.MainSystem;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ApplicantManager extends UserManager<Applicant> implements Serializable {
    private static final long serialVersionUID = -1825044094227184815L;

    public ApplicantManager(MainSystem system) {
        super(system);
    }

}
