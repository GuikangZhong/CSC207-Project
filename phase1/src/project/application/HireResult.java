package project.application;

import project.user.Applicant;

import java.util.ArrayList;
import java.util.List;

public class HireResult {
    private List<Applicant> hired;

    HireResult() {
        hired = new ArrayList<>();
    }

    void addHiredApplicant(Applicant applicant) {
        this.hired.add(applicant);
    }

    public List<Applicant> getHired() {
        return hired;
    }
}
