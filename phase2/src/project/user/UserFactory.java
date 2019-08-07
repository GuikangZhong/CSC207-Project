package project.user;

import project.application.Company;

import java.util.List;

public class UserFactory {
    public User createUser(String username,
                           String password,
                           String realName,
                           List<Company> company,
                           String type){
        if (type.equals("HR")){
            return new HR(username, password, realName, company);
        }
        else if (type.equals("Interviewer")){
            return new Interviewer(username, password, realName, company);
        }
        else if (type.equals("Referee")){
            return new Referee(username, password, realName, company);
        }
        else{
            throw new RuntimeException("Unrecognized user type!");
        }
    }

    public User createUser(ApplicantHistory history, String username, String password, String realName,
                           List<Company> company, String type){
        if (type.equals("Applicant")){
            return new Applicant(history, username, password, realName, company);
        }
        else {
            throw new RuntimeException("Unrecognized user type.");
        }
    }
}
