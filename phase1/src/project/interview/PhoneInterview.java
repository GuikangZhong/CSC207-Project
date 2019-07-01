package project.interview;

import java.io.Serializable;
import java.util.List;

public class PhoneInterview extends Interview implements Serializable {
    private static final long serialVersionUID = -7664633895954684324L;

    PhoneInterview() {
        super(1);
    }

    @Override
    public String getInterviewType() {
        return "Phone Interview";
    }
}
