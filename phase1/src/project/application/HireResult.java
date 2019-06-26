package project.application;

import project.user.Applicant;

import java.util.List;

public class HireResult {
    List<Applicant> hired;

    public HireResult(List<Applicant> hired) {
        this.hired = hired;
    }

    public List<Applicant> getHired() {
        return hired;
    }
}
