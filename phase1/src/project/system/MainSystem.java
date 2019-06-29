package project.system;

import project.application.Company;
import project.application.JobPosting;
import project.user.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MainSystem implements Serializable {

    public SystemClock getClock() {
        return clock;
    }

    private SystemClock clock;

    private HashMap<String, Company> companies;
    private ApplicantManager applicants;

    public MainSystem() {
        clock = new SystemClock();
    }

    User login(String name, String password) {
        if (applicants.containsUser(name)) {
            return applicants.signIn(name, password);
        } else {
            for (Company company : companies.values()) {
                if (company.getHrManager().containsUser(name)) {
                    return company.getHrManager().signIn(name,password);
                }
                if (company.getInterviewerManager().containsUser(name)) {
                    return company.getInterviewerManager().signIn(name,password);
                }
            }
            return null;
        }
    }

    Collection<Company> getCompanies() {
        return companies.values();
    }

}
