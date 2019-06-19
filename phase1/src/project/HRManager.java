package project;

public class HRManager extends UserManager<HR> {


    @Override
    HR createUser(UserHistory history, String name, String password) {
        return new HR(history,name,password);
    }
}
