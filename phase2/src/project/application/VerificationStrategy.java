package project.application;

import java.io.Serializable;

@FunctionalInterface
public interface VerificationStrategy extends Serializable {
    /**
     *
     * @param application: the application to be verified whether contains all the required documents.
     * @return true if given application satisfies the VerificationStrategy
     */
    boolean satisfies(Application application);
}
