package project.application;

public class BasicRequirement implements Requirement {
    private static final long serialVersionUID = 7376917471657116219L;

    @Override
    public boolean satisfies(Application application) {
        int cnt = 0;
        for (Document document : application.getDocument()) {
            if (document.type().equals(CV.createEmpty().type())
                    || document.type().equals(CoverLetter.createEmpty().type())) {
                cnt++;
            }
        }
        return cnt == 2;
    }
}
