package project.application;

public class BasicRequirement implements Requirement {
    private static final long serialVersionUID = 7376917471657116219L;

    @Override
    public boolean satisfies(Application application) {
        return application.getDocument() != null;
    }
}
