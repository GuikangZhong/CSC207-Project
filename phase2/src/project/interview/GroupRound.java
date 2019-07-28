package project.interview;

public class GroupRound extends Round implements Cloneable {
    @Override
    public String getRoundType() {
        return roundType();
    }

    @Override
    public int getMaxRoundNumber() {
        return 5;
    }

    public static String roundType(){
        return "Group";
    }
}
