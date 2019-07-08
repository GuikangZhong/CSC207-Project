package project.interview;

import project.application.Job;

public class InPersonRound extends Round {
    private static final long serialVersionUID = -466488373417479710L;

    public InPersonRound(Job job){
        super(job);
    }
    @Override
    public String roundType() {
        return "In Person";
    }

    @Override
    public int getMaxRoundNumber() {
        return 3;
    }
}
