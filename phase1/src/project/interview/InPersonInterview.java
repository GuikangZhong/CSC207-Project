package project.interview;

import java.util.List;

public class InPersonInterview extends Interview {

    private static final long serialVersionUID = 599715767258542866L;

    InPersonInterview() {
        super(3);
    }

    @Override
    public String getInterviewType() {
        return "Phone Interview";
    }
}
