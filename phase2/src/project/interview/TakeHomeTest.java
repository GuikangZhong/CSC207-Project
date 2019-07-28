package project.interview;

public class TakeHomeTest extends Round implements Cloneable {
    public static String roundType(){return "Take-home test";}

    @Override
    public String getRoundType() {
        return roundType();
    }

    @Override
    public int getMaxRoundNumber() {
        return 5;
    }
}
