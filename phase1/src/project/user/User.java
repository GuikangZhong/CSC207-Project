package project.user;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public abstract class User implements Serializable {
    private static final long serialVersionUID = -5007096129053697211L;

    private UserHistory history;
    private String username;
    private String realName;
    private String company;
    private byte[] password;

    public User(UserHistory history,
                String username,
                String password,
                String realName,
                String company) {
        this.history = history;
        this.realName = realName;
        this.username = username;
        this.company = company;
        try {
            this.password = getMD5ForPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Are you kidding me?");
        }
    }

    private static byte[] getMD5ForPassword(String password) throws NoSuchAlgorithmException {
        String salt = password + "ytv0cfj56t7890f3whntcw39v8v 6789";
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(salt.getBytes());
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRealName() {
        return realName;
    }

    public abstract Type getType();

    public String getUsername() {
        return this.username;
    }

    public UserHistory getHistory() {
        return this.history;
    }

    public final boolean verifyPassword(String password) {
        try {
            byte[] digest = getMD5ForPassword(password);
            return Arrays.equals(this.password, digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Are you kidding me?");
        }
    }

    public enum Type {
        NONE,
        APPLICANT,
        HR,
        INTERVIEWER
    }

}
