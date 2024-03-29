package project.application;

import java.io.*;
import java.time.LocalDateTime;

public class CoverLetter extends Document implements Serializable {
    private static final long serialVersionUID = 4089162745833731740L;
    private static final int maxLetters = 1;

    public CoverLetter(){
        super();
    }

    public CoverLetter(String name, String content, LocalDateTime date) {
        super(name, content, date);
    }

    public static String documentType(){
        return "CoverLetter";
    }

    @Override
    public String getDocumentType() {
        return documentType();
    }

    @Override
    public int maxNumber() {
        return maxLetters;
    }
}
