package project.application;

import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReferenceLetter extends Document implements Serializable {
    private static final long serialVersionUID = -8264798408812116962L;

    private Applicant toApplicant;
    private JobPosting jobPosting;

    public ReferenceLetter(){
        super();
    }

    public ReferenceLetter(String name, String content, LocalDateTime date, Applicant applicant, JobPosting jobPosting) {
        super(name, content, date);
        this.toApplicant = applicant;
        this.jobPosting = jobPosting;
    }

    static String documentType() {
        return "ReferenceLetter";
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    @Override
    public String getDocumentType() {
        return documentType();
    }

    @Override
    public int maxNumber() {
        return 1;
    }
}
