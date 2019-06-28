package project.user;

import project.system.SystemClock;

public class ApplicantManager extends UserManager<Applicant> {
    @Override
    Applicant createUser(String name, String password) {
        return new Applicant(null, name, password);
    }
    private SystemClock clock;

    public ApplicantManager(SystemClock clock) {
    	this.clock = clock;
    }
    
    void checkExpiredDocument(){

    }
}
