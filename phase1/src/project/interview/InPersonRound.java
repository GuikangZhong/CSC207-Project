package project.interview;

public class InPersonRound extends Round {
    private static final long serialVersionUID = -466488373417479710L;

    public InPersonRound(int number){
        super(number);
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
