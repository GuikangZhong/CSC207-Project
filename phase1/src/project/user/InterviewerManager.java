package project.user;

import project.application.Company;
import project.system.MainSystem;

import java.io.Serializable;

public class InterviewerManager extends UserManager<Interviewer>implements Serializable {
    private static final long serialVersionUID = -7938932127116069939L;
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
