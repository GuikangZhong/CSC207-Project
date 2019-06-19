package project;

import java.util.Collection;
import java.util.List;

public class MainSystem {
    public Collection<Company> getCompanys() {
        return companys;
    }

    public Collection<Applicant> getApplicants() {
        return applicants;
    }

    public Collection<HR> getHRs() {
        return HRs;
    }

    public Collection<Interviewer> getInterviewers() {
        return interviewers;
    }

    private Collection<Company>companys;
    private Collection<Applicant> applicants;
    private Collection<HR> HRs;
    private Collection<Interviewer> interviewers;


}
