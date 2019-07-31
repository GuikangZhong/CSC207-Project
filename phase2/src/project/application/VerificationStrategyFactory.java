package project.application;

public class VerificationStrategyFactory {
    public VerificationStrategy getStrategy(String type){
        if (type.equals("Basic")){
            return new BasicVerificationStrategy();
        }
        else if (type.equals("Reference")){
            return new ReferenceVerificationStrategy();
        }
        return null;
    }
}
