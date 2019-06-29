package project.user;

import project.application.Company;
import project.system.MainSystem;
import project.system.SystemClock;

public class ApplicantManager extends UserManager<Applicant> {
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

    void checkExpiredDocument() {

    }
}
