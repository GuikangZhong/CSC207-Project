package project.user;

import project.system.MainSystem;
import project.system.SystemClock;

import java.io.Serializable;
import java.time.Clock;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

abstract public class UserManager<T extends User> implements Serializable {
    private static final long serialVersionUID = -3896729601031943756L;

    HashMap<String, T> users;
    private MainSystem system;

    public UserManager(MainSystem system) {
        this.system = system;
        users = new HashMap<>();
    }

    public MainSystem getSystem() {
        return system;
    }

    public boolean addUser(T user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public T getUser(String username) {
        return users.get(username);
    }

    public HashMap<String, T> getUsers() {
        return users;
    }

    public boolean containsUser(String name) {
        return users.containsKey(name);
    }

}
