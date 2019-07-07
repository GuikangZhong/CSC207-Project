package project.interview;

public class PhoneRound extends Round {
    private static final long serialVersionUID = -9131759850246001085L;

    public PhoneRound(int number){
        super(number);
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
