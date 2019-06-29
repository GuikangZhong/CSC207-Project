package project.user;

import project.application.*;
import project.interview.*;
import project.system.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class HR extends User {
    private Company company;
    private List<List<Application>> recommendationLists;
    // TODO: check with piazza / prof if this is really what it wants

    HR(UserHistory history, String username, String password, Company company) {
        super(history, username, password, company.getSystem().getClock());
        this.company = company;
        recommendationLists = new ArrayList<>();
    }

    @Override
    public Type getType() {
        return Type.HR;
    }

    public List<List<Application>> getRecommendationLists() {
        return recommendationLists;
    }

    void updateOnInterviewFinished(){

    }


}
