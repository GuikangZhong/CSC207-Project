package project.application;

import java.io.*;
import java.time.LocalDateTime;

public class CV extends Document implements Serializable {
    private static final long serialVersionUID = 6736238269987565017L;

    public CV(){
        super();
    }

    public CV(String name, String content, LocalDateTime date) {
        super(name, content, date);
    }

    @Override
    public String type() {
        return "CV";
    }

    @Override
    public int maxNumber() {
        return 1;
    }
}
