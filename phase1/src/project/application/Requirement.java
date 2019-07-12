package project.application;

import java.io.Serializable;

@FunctionalInterface
public interface Requirement extends Serializable {
    /**
     *
     * @param application
     * @return true if given application satisfies the Requirement
     */
    boolean satisfies(Application application);
}
