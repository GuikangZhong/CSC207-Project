package project.application;

import java.io.Serializable;

@FunctionalInterface
public interface VerificationStrategy extends Serializable {
    /**
     *
     * @param application
     * @return true if given application satisfies the VerificationStrategy
     */
    boolean satisfies(Application application);
}
