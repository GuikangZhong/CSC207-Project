package project;

@FunctionalInterface
public interface Requirement {
    boolean satisfies(Application application);
}
