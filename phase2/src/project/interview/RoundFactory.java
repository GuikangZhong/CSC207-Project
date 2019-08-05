package project.interview;

public class RoundFactory {
    public Round createRound(String roundType){
        if (roundType.equals(TakeHomeTest.roundType())){
            return new TakeHomeTest();
        }
        else if (roundType.equals(InPersonRound.roundType())){
            return new InPersonRound();
        }
        else if (roundType.equals(PhoneRound.roundType())){
            return new PhoneRound();
        }
        else if (roundType.equals(GroupRound.roundType())){
            return new GroupRound();
        }
        throw new RuntimeException("Unrecognizable round type: "+roundType);
    }
}
