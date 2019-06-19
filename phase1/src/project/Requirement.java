package project;

@FunctionalInterface
public interface Requirement {
    boolean satisfies(Applicant applicant);
}
