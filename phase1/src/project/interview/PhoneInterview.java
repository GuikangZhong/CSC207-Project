package project.interview;

import java.util.List;

public class PhoneInterview extends Interview{
    PhoneInterview() {
        super(1);
    }

    @Override
    public String getInterviewType() {
        return "Phone Interview";
    }
}
