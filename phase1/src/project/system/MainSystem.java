package project.system;

import project.application.Company;
import project.application.JobPosting;
import project.user.*;

import java.io.*;
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

    public void createCompany(String name){
        companies.put(name, new Company(name, this));
    }

    public Applicant signUpApplicant(String username, String password, String realname){
        return applicants.signUp(username, password);
    }

    public User signUp(User.Type type, String companyName, String username, String password, String realname){
        Company company = companies.get(companyName);
        if(type == User.Type.HR){
            return company.getHrManager().signUp(username, password);
        }else if(type == User.Type.INTERVIEWER){
            return company.getInterviewerManager().signUp(username, password);
        }
        throw new RuntimeException("What type have you entered???");
    }

    public User login(String name, String password) {
        if (applicants.containsUser(name)) {
            return applicants.signIn(name, password);
        } else {
            for (Company company : companies.values()) {
                if (company.getHrManager().containsUser(name)) {
                    return company.getHrManager().signIn(name, password);
                }
                if (company.getInterviewerManager().containsUser(name)) {
                    return company.getInterviewerManager().signIn(name, password);
                }
            }
            return null;
        }
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
}
