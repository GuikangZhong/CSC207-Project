package project.interview;

public class GroupRound extends Round implements Cloneable {
    @Override
    public String getRoundType() {
        return roundType();
    }

    public static String roundType(){
        return "Group";
    }
}
