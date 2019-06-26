package project.user;

public class HR extends User {
    HR(UserHistory history, String username, String password){
        super(history, username,password);
    }
    @Override
    public Type getType(){return Type.HR;}
}
