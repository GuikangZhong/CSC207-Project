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
    private UserManager<Applicant> applicants;

    MainSystem() {
        clock = new SystemClock();
    }



    void signUp(User.Type type, String name, String password){
        // TODO:
    }
    User login(String name, String password){
        User user = null;
         if(applicants.containsUser(name)){
             user = applicants.getUser(name);
         }else {

             for (Company company : companies.values()) {
                 if (company.getHrManager().containsUser(name)) {
                     user = company.getHrManager().getUser(name);
                     break;
                 }
                 if (company.getInterviewerManager().containsUser(name)) {
                     user = company.getInterviewerManager().getUser(name);
                     break;
                 }
             }
         }
         if(null != user && user.verifyPassword(password)){
             return user;
         }
         return null;
    }
    Collection<Company> getCompanies(){
        return companies.values();
    }

}
