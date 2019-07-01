package project.user;

import project.system.SystemClock;
import project.user.UserHistory;

import java.io.Serializable;

public abstract class User<T extends UserHistory> implements Serializable {
    private static final long serialVersionUID = -5007096129053697211L;

    public enum Type {
        NONE,
        APPLICANT,
        HR,
        INTERVIEWER
    }

    private T history;
    private String username;
    private String password;

    public String getRealName() {
        return realName;
    }

    private String realName;
    private SystemClock clock;

    public User(T history, String username, String password, SystemClock clock) {
        this.username = username;
        this.password = password;
        this.clock = clock;
    }

    public abstract Type getType();

    public String getUsername() {
        return this.username;
    }

    public T getHistory() {
        return this.history;
    }

    public final boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

}
