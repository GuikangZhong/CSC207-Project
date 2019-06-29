package project.user;

import project.application.Company;
import project.system.MainSystem;

public class InterviewerManager extends UserManager<Interviewer> {
    private Company company;

    public InterviewerManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
    }

    @Override
    Interviewer createUser(String name, String password) {
        return new Interviewer(new UserHistory(getSystem().getClock().getClock()), name, password, company);
    }
}
