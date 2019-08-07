package project.interview;


public class InPersonRound extends Round implements Cloneable{
    private static final long serialVersionUID = -466488373417479710L;

    public InPersonRound(){super();}

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
