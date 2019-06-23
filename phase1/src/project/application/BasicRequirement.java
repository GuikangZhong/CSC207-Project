package project.application;

public class BasicRequirement implements Requirement {
    @Override
    public boolean satisfies(Application application){
        return application.getDocument() != null;
    }
}
