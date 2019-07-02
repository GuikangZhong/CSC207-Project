package project.user;

import project.application.Company;
import project.system.MainSystem;

public class InterviewerManager extends UserManager<Interviewer> {
    private static final long serialVersionUID = 7347046775471127619L;
    private Company company;

    public InterviewerManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
    }

    @Override
    public Interviewer createUser(String name, String password) {
        return new Interviewer(new UserHistory(getSystem().getClock().getClock()), name, password, company);
    }
}
