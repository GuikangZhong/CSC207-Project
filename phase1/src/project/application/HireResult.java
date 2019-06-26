package project.application;

import project.user.Applicant;

import java.util.List;

public class HireResult {
    public List<Applicant> getHired() {
        return hired;
    }

    List<Applicant> hired;

    public HireResult(List<Applicant> hired) {
        this.hired = hired;
    }
}
