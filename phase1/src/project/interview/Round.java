package project.interview;

import project.application.Company;

import java.io.Serializable;
import java.util.List;

public abstract class Round implements Serializable {
    private static final long serialVersionUID = 754458654121081552L;

    public abstract String roundType();
    public abstract int getMaxRoundNumber();

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    public int getNumber() {
        return number;
    }

    public List<InterviewGroup> getGroups() {
        return groups;
    }

    private List<InterviewGroup> groups;
    private Interview interview;

    boolean isAllGroupsSubmitted(){
        for(InterviewGroup group : groups){
            if(!group.isSubmitted())
                return false;
        }
        return true;
    }
    void updateOnGroupSubmitted(){
        if(isAllGroupsSubmitted()){
            interview.updateOnRoundFinished();
        }
    }

}
