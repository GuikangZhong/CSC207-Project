package project.interview;

import project.application.Job;

public class PhoneRound extends Round {
    private static final long serialVersionUID = -9131759850246001085L;

    public PhoneRound(Job job) {
        super(job);
    }

    @Override
    public String roundType() {
        return "Phone";
    }

    @Override
    public int getMaxRoundNumber() {
        return 1;
    }
}
