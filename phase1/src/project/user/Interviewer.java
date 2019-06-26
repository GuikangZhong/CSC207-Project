package project.user;

public class Interviewer extends User {
    Interviewer(UserHistory history, String username, String password) {
        super(history, username, password);
    }
    @Override
    public Type getType(){return Type.INTERVIEWER;}
}
