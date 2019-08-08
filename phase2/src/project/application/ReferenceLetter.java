package project.application;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReferenceLetter extends Document implements Serializable {
    private static final long serialVersionUID = -8264798408812116962L;
    private static final int maxReferences = 100;
    private JobPosting jobPosting;

    public ReferenceLetter(String name, String content, LocalDateTime date) {
        super(name, content, date);
    }

    public static String documentType() {
        return "ReferenceLetter";
    }

    public void setJobPosting(JobPosting jobPosting){
        this.jobPosting = jobPosting;
    }

    public JobPosting getJobPosting(){
        return jobPosting;
    }


    @Override
    public String getDocumentType() {
        return documentType();
    }

    @Override
    public int maxNumber() {
        return maxReferences;
    }
}
