package project.application;

import project.user.Applicant;

import java.util.List;

public class HireResult {
    List<Applicant> hired;

    void addHiredApplicant(Applicant applicant){
        this.hired.add(applicant);
    }

    public List<Applicant> getHired() {
        return hired;
    }
}
