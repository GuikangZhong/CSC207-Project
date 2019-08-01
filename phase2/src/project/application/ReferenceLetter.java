package project.application;

import project.user.Applicant;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReferenceLetter extends Document implements Serializable {
    private static final long serialVersionUID = -8264798408812116962L;

    public ReferenceLetter(String name, String content, LocalDateTime date) {
        super(name, content, date);
    }

    static String documentType() {
        return "ReferenceLetter";
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
