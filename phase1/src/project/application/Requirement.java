package project.application;

import java.io.Serializable;

@FunctionalInterface
public interface Requirement extends Serializable {
    boolean satisfies(Application application);
}
