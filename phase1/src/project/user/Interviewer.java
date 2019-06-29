package project.user;

import project.application.Company;

public class Interviewer extends User {
    private Company company;

    Interviewer(UserHistory history, String username, String password, Company company) {
        super(history, username, password, company.getSystem().getClock());
        this.company = company;
    }

    @Override
    public Type getType() {
        return Type.INTERVIEWER;
    }
}
