package project.interview;

import project.application.Job;

public class InPersonRound extends Round {
    private static final long serialVersionUID = -466488373417479710L;

    public static String roundType(){
        return "In Person";
    }
    @Override
    public String getRoundType() {
        return roundType();
    }

    @Override
    public int getMaxRoundNumber() {
        return 3;
    }
}
