package project.interview;

import java.io.Serializable;
import java.util.List;

public class InPersonInterview extends Interview implements Serializable {

    private static final long serialVersionUID = 4979548900357015567L;

    InPersonInterview() {
        super(3);
    }

    @Override
    public String getInterviewType() {
        return "Phone Interview";
    }
}
