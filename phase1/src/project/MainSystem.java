package project;

import project.user.Applicant;
import project.user.HR;
import project.user.Interviewer;
import project.user.UserManager;

import java.io.Serializable;
import java.util.Collection;


public class MainSystem implements Serializable {
    public Collection<Company> getCompanys() {
        return companys;
    }

    public SystemClock getClock() {
        return clock;
    }

    private SystemClock clock;

    private Collection<Company>companys;
    private UserManager<Applicant> applicants;
    private UserManager<HR> HRs;
    private UserManager<Interviewer> interviewers;

    MainSystem(){
         clock = new SystemClock();
    }

}
