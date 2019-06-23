package project.application;

@FunctionalInterface
public interface Requirement {
    boolean satisfies(Application application);
}
