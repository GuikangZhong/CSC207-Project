package project;

public class InterviewerManager extends UserManager<Interviewer> {
    @Override
    Interviewer createUser(String name, String password) {
        return new Interviewer(null,name,password);
    }
}
