package project.user;

import project.system.MainSystem;
import project.system.SystemClock;

import java.io.Serializable;
import java.time.Clock;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

abstract public class UserManager<T extends User>  implements Serializable {

    private static final long serialVersionUID = -3896729601031943756L;
    protected HashMap<String, T> users;

    public MainSystem getSystem() {
        return system;
    }

    private MainSystem system;

    public UserManager(MainSystem system) {
        this.system = system;
        users = new HashMap<>();
    }

    // Type erasure doesn't allow us to call T's constructor :(
    public abstract T createUser(String name, String password);

    public T signUp(String name, String password){
        T user = createUser(name, password);
        addUser(user);
        return user;
    }

    void addUser(T user) {
        if(users.containsKey(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        users.put(user.getUsername(), user);
    }

    public T signIn(String name, String password) {
        if (users.containsKey(name)) {
            T user = users.get(name);
            if (user.verifyPassword(password)) {
                return user;
            }
        }
        throw new RuntimeException("Password or user name not correct");
    }

    public boolean containsUser(String name) {
        return users.containsKey(name);
    }

    public T getUser(String name) {
        return users.get(name);
    }

    Clock getClock() {
        return getSystemClock().getClock();
    }

    SystemClock getSystemClock() {
        return system.getClock();
    }
}
