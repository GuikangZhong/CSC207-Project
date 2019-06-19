package project;

import java.time.Clock;
import java.util.Collection;
import java.util.List;

public class MainSystem {
    public Collection<Company> getCompanys() {
        return companys;
    }


    private Collection<Company>companys;
    private UserManager<Applicant> applicants;
    private UserManager<HR> HRs;
    private UserManager<Interviewer> interviewers;



}
