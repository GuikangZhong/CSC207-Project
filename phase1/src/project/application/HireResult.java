package project.application;

import project.user.Applicant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HireResult implements Serializable {
    private static final long serialVersionUID = -102762939528544350L;
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
