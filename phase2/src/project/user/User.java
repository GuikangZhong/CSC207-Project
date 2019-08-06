package project.user;


import project.application.Company;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public abstract class User implements Serializable {
    private static final long serialVersionUID = -5007096129053697211L;

    private UserHistory history;
    private String username;
    private String realName;
    private List<Company> companies;
    private Company signedInCompany;
    private byte[] password;

    public User(UserHistory history,
                String username,
                String password,
                String realName,
                List<Company> companies) {
        this.history = history;
        this.realName = realName;
        this.username = username;
        this.companies = companies;
        try {
            this.password = getMD5ForPassword(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No MD5 algorithm found");
        }
    }
    private static byte[] getMD5ForPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(password.getBytes());
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void addCompany(Company company){
        companies.add(company);
    }

    public Company getSignedInCompany(){
        return signedInCompany;
    }

    public void setSignedInCompany(Company signedInCompany){
        this.signedInCompany = signedInCompany;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
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
            throw new RuntimeException("No MD5 algorithm found");
        }
    }



    public enum Type {
        APPLICANT,
        HR,
        INTERVIEWER,
        REFEREE
    }

}
