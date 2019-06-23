package project;

public class ApplicantManager extends UserManager<Applicant> {
    @Override
    Applicant createUser(String name, String password) {
        return new Applicant(null, name, password);
    }

    void checkExpiredDocument(){

    }
}
