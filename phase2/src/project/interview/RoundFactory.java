package project.interview;

public class RoundFactory {
    public Round createRound(String roundType){
        if (roundType.equals("Take-home test")){
            return new TakeHomeTest();
        }
        else if (roundType.equals("In-person Interview")){
            return new InPersonRound();
        }
        else if (roundType.equals("Phone Interview")){
            return new PhoneRound();
        }
        else if (roundType.equals("Group Interview")){
            return new GroupRound();
        }
        return null;
    }
}
