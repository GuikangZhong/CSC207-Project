package project.interview;


public class PhoneRound extends Round implements Cloneable{
    private static final long serialVersionUID = -9131759850246001085L;

    public PhoneRound(){super();}

    public static String roundType(){
        return "Phone";
    }
    @Override
    public String getRoundType() {
        return roundType();
    }

}
