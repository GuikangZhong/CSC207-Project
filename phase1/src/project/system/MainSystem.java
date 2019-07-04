package project.system;

import project.application.Company;
import project.application.JobPosting;
import project.user.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class MainSystem implements Serializable {

    private static final long serialVersionUID = 8741196022318228487L;

    public SystemClock getClock() {
        return clock;
    }

    private SystemClock clock;

    private HashMap<String, Company> companies;
    private ApplicantManager applicants;

    public MainSystem() {
        clock = new SystemClock();
        companies = new HashMap<>();
        applicants = new ApplicantManager(this);
    }

    public void addCompany(String name) {
        companies.put(name, new Company(name, this));
    }

    public User getUser(String username) {
        if (applicants.containsUser(username))
            return applicants.getUser(username);
        for (Company company : getCompanies()) {
            User user = company.getUser(username);
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    public boolean addUser(User user) {
        if (user.getType() == User.Type.APPLICANT) {
            return applicants.addUser((Applicant) user);
        } else {
            Company company = getCompany(user.getCompany());
            return company.addUser(user);
        }
    }

    public Company getCompany(String name) {
        return companies.get(name);
    }

    public Collection<Company> getCompanies() {
        return companies.values();
    }

    public void serializeToFile(String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this);
    }

    public static MainSystem loadFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);
        return (MainSystem) in.readObject();
    }

    LocalDateTime now() {
        return LocalDateTime.now(clock.getClock());
    }
}
