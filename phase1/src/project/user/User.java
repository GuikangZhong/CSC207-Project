package project.user;

import project.system.SystemClock;
import project.user.UserHistory;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
    private byte[] password;

    public String getRealName() {
        return realName;
    }

    private String realName;
    private SystemClock clock;

    private static byte[] getMD5ForPassword(String password) throws NoSuchAlgorithmException{
        String salt = password + "ytv0cfj56t7890f3whntcw39v8v 6789";
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(salt.getBytes());
    }
    public User(T history, String username, String password, SystemClock clock) {
        this.username = username;
        this.clock = clock;
        this.history = history;
        try {
            this.password = getMD5ForPassword(password);
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Are you kidding me?");
        }
    }

    public abstract Type getType();

    public String getUsername() {
        return this.username;
    }

    public T getHistory() {
        return this.history;
    }

    public final boolean verifyPassword(String password) {
        try {
            byte[] digest = getMD5ForPassword(password);
            return Arrays.equals(this.password, digest);
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Are you kidding me?");
        }
    }

}
