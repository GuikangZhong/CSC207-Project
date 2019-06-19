package project;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

abstract public class UserManager<T extends User> {
    HashMap<String, T> users;

    // Type erasure doesn't allow us to call T's constructor :(
    abstract T createUser(UserHistory history, String name, String password);


    abstract UserHistory createUseHistory();

    T signIn(String name, String password) {
        if (users.containsKey(name)) {
            T user = users.get(name);
            if (user.verifyPassword(password)) {
                return user;
            }
            throw new RuntimeException("Password or user name not correct");
        }
        T user = createUser(createUseHistory(), name, password);
        users.put(name, user);
        return user;
    }

    Collection<T> toCollection() {
        return Collections.unmodifiableCollection(users.values());
    }
}
