package project.user;

import project.application.Company;
import project.interview.InterviewAssignment;

import java.util.Collections;
import java.util.List;

public class Interviewer extends User {
    private static final long serialVersionUID = 6252452179878258209L;

    public Company getCompany() {
        return company;
    }

    private Company company;

    Interviewer(UserHistory history, String username, String password, Company company) {
        super(history, username, password, company.getSystem().getClock());
        this.company = company;
    }

    @Override
    public Type getType() {
        return Type.INTERVIEWER;
    }

    public List<InterviewAssignment> getInterviews() {
        return Collections.unmodifiableList(interviews);
    }

    public void setInterviews(List<InterviewAssignment> interviews) {
        this.interviews = interviews;
    }

    private List<InterviewAssignment> interviews;
}
