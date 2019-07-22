package project.application;

import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReferenceLetter extends Document implements Serializable {
    private static final long serialVersionUID = -8264798408812116962L;

    Applicant toApplicant;

    public ReferenceLetter(){
        super();
    }

    public ReferenceLetter(String name, String content, LocalDateTime date, Applicant applicant) {
        super(name, content, date);
        toApplicant = applicant;
    }

    @Override
    public String type() {
        return "ReferenceLetter";
    }

    @Override
    public int maxNumber() {
        return 1;
    }
}
