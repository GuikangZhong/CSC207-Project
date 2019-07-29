package project.application;

/**
 * Checks if given application contains a CV and a CoverLetter
 */
public class BasicVerificationStrategy implements VerificationStrategy {
    private static final long serialVersionUID = 7376917471657116219L;

    @Override
    public boolean satisfies(Application application) {
        int cnt = 0;
        for (Document document : application.getDocument()) {
            if (document.getDocumentType().equals(CV.documentType())
                    || document.getDocumentType().equals(CoverLetter.documentType())) {
                cnt++;
            }
        }
        return cnt == 2;
    }
}
