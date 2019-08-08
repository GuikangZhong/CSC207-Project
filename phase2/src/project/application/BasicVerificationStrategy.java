package project.application;

/**
 * Checks if given application contains a CV and a CoverLetter
 */
public class BasicVerificationStrategy implements VerificationStrategy {
    private static final long serialVersionUID = 7376917471657116219L;


    /**
     * This verification passes if and only if the application contains exactly one CV and one cover letter.
     *
     * In our design, it is impossible that the application contains two CV's or two cover letters since the maximum
     * allowed number for both of the two documents are 1.
     * @param application: The application being verified.
     * @return true if and only if the application contains one CV and one Cover Letter.
     */
    @Override
    public boolean satisfies(Application application) {
        int requiredNum = 2;
        int cnt = 0;
        for (Document document : application.getDocument()) {
            if (document.getDocumentType().equals(CV.documentType())
                    || document.getDocumentType().equals(CoverLetter.documentType())) {
                cnt++;
            }
        }
        return cnt == requiredNum;
    }
}
