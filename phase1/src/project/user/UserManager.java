package project.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

abstract public class UserManager<T extends User> {

    private HashMap<String, T> users;

    // Type erasure doesn't allow us to call T's constructor :(
    abstract T createUser(String name, String password);

    T signIn(String name, String password) {
        if (users.containsKey(name)) {
            T user = users.get(name);
            if (user.verifyPassword(password)) {
                return user;
            }
            throw new RuntimeException("Password or user name not correct");
        }
        T user = createUser(name, password);
        users.put(name, user);
        return user;
    }

    public boolean containsUser(String name){
        return users.containsKey(name);
    }

    public User getUser(String name){
        return users.get(name);
    }
}
