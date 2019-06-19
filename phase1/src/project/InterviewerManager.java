package project;

public class InterviewerManager extends UserManager<Interviewer> {
    @Override
    Interviewer createUser(UserHistory history, String name, String password) {
        return new Interviewer(history,name,password);
    }
}
