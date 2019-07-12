package project.application;

/**
 * Checks if given application contains a CV and a CoverLetter
 */
public class BasicRequirement implements Requirement {
    private static final long serialVersionUID = 7376917471657116219L;

    @Override
    public boolean satisfies(Application application) {
        int cnt = 0;
        for (Document document : application.getDocument()) {
            if (document.type().equals(new CV().type())
                    || document.type().equals(new CoverLetter().type())) {
                cnt++;
            }
        }
        return cnt == 2;
    }
}
