package project.system;

import project.application.*;
import project.interview.Interview;
import project.observer.SystemObserver;
import project.user.*;
import project.utils.Logging;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;


public class MainSystem implements Serializable {

    private static final long serialVersionUID = 8741196022318228487L;

    public SystemClock getClock() {
        return clock;
    }

    private SystemClock clock;

    private List<SystemObserver> observers;
    private HashMap<String, Company> companies;
    private ApplicantManager applicants;

    public MainSystem() {
        clock = new SystemClock();
        companies = new HashMap<>();
        applicants = new ApplicantManager(this);
        observers = new ArrayList<>();
        addObserver(applicants);
    }

    public boolean addCompany(String name) {
        if (!companies.containsKey(name)) {
            Company company = new Company(name, this);
            companies.put(name, company);
            logger.info("added company " + name);
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
        logger.info("Added user : " + user.getUsername() + " type: " + user.getType() + " company: " + user.getCompany());
        if (user.getType() == User.Type.APPLICANT) {
            addObserver((Applicant) user);
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
        logger.info("Added SystemObserver " + observer);
        observers.add(observer);
    }

    static private Logger logger = Logging.getLogger();

    public void setSystemClockTime(LocalDateTime time) {
        logger.info("Set time to " + now().toString());
        Duration duration = Duration.between(now(), time);
        getClock().offset(duration);
        notifyOnTimeUpdate();
    }

    public void notifyOnTimeUpdate() {
        for (SystemObserver observer : observers) {
            observer.updateOnTime(now());
        }
    }
}
