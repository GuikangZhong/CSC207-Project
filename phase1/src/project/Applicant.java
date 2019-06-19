package project;

import java.util.List;

public class Applicant extends User {
    private Document document;
    private List<Application> applications;
    Applicant(String username, String password){
        super(username,password);
    }
}
