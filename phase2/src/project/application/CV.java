package project.application;

import java.io.*;
import java.time.LocalDateTime;

public class CV extends Document implements Serializable {
    private static final long serialVersionUID = 6736238269987565017L;
    private static final int maxCVs = 1;

    public CV() {
        super();
    }

    public CV(String name, String content, LocalDateTime date) {
        super(name, content, date);
    }

    public static String documentType() {
        return "CV";
    }

    @Override
    public String getDocumentType() {
        return documentType();
    }

    @Override
    public int maxNumber() {
        return maxCVs;
    }
}
