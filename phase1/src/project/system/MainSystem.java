package project.system;

import project.application.Company;
import project.application.JobPosting;
import project.application.JobPostingManager;
import project.observer.SystemObserver;
import project.user.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class MainSystem implements Serializable {

    private static final long serialVersionUID = 8741196022318228487L;

    public SystemClock getClock() {
        return clock;
    }

    private SystemClock clock;

    private List<SystemObserver> observers;
    private HashMap<String, Company> companies;
    private ApplicantManager applicants;

    private void test() {
        String[] companies = {"UofT", "Microsoft", "Google"};
        String names = "abcdefghijklmnopqrstuvwxyz";
        int index = 0;
        for (String c : companies) {
            addCompany(c);
            for (int i = 0; i < 8; i++) {
                addUser(new Applicant(new ApplicantHistory(now()),
                        "" + names.charAt(index),
                        "a", "a", c));
                index++;
            }
        }
    }

    public MainSystem() {
        clock = new SystemClock();
        companies = new HashMap<>();
        applicants = new ApplicantManager(this);
        observers = new ArrayList<>();
        addObserver(applicants);
        test();
    }

    public boolean addCompany(String name) {
        if (getCompany(name) == null) {
            Company company = new Company(name, this);
            companies.put(name, company);
            addObserver(company);
            return true;
        }
        return false;
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

    public HashMap<String, Applicant> getApplicants() {
        return applicants.getUsers();
    }

    public Collection<Company> getCompanies() {
        return companies.values();
    }

    public List<JobPosting> getAllJobPostings() {
        Collection<Company> companies = getCompanies();
        List<JobPosting> jobPostings = new ArrayList<>();
        for (Company company : companies) {
            JobPostingManager jobPostingManager = company.getJobPostingManager();
            Collection<JobPosting> jp = jobPostingManager.getJobPostings().values();
            if (jp.size() != 0) {
                jobPostings.addAll(jp);
            }
        }
        return jobPostings;
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

    public LocalDateTime now() {
        return LocalDateTime.now(clock.getClock());
    }

    public void addObserver(SystemObserver observer) {
        observers.add(observer);
    }

    void notifyOnTimeUpdate() {
        for (SystemObserver observer : observers) {
            observer.updateOnTime(now());
        }
    }
}
