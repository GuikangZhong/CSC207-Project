package project;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


public class MainSystem implements Serializable {
    public Collection<Company> getCompanys() {
        return Collections.unmodifiableCollection(companys);
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
