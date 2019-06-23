package project.user;

public class HRManager extends UserManager<HR> {


    @Override
    HR createUser(String name, String password) {
        return new HR(null, name,password);
    }
}
