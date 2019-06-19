package project;

public class ApplicantManager extends UserManager<Applicant> {
    @Override
    Applicant createUser(UserHistory history, String name, String password) {
        return new Applicant(history,name,password);
    }
}
