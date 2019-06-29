package project.user;

import project.application.Company;
import project.system.MainSystem;

public class HRManager extends UserManager<HR> {
    private Company company;

    public HRManager(MainSystem system, Company company) {
        super(system);
        this.company = company;
    }

    @Override
    HR createUser(String name, String password) {
        return new HR(new UserHistory(getSystem().getClock().getClock()), name, password, company);
    }
}
