package project.interview;

import java.util.List;

public class InPersonInterview extends Interview {

    InPersonInterview() {
        super(3);
    }

    @Override
    public String getInterviewType() {
        return "Phone Interview";
    }
}
