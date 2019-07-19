package project.application;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReferenceLetter extends Document implements Serializable {
    private static final long serialVersionUID = -8264798408812116962L;

    public ReferenceLetter(){
        super();
    }

    public ReferenceLetter(String name, String content, LocalDateTime date) {
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
