package project.user;

import project.system.MainSystem;
import project.system.SystemClock;

import java.time.Clock;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

abstract public class UserManager<T extends User> {

    protected HashMap<String, T> users;

    public MainSystem getSystem() {
        return system;
    }

    private MainSystem system;

    public UserManager(MainSystem system) {
        this.system = system;
    }

    // Type erasure doesn't allow us to call T's constructor :(
    abstract T createUser(String name, String password);


    void addUser(T user) {
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
