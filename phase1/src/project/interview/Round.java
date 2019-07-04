package project.interview;

import project.application.Company;
import project.user.Interviewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Round implements Serializable {

    private static final long serialVersionUID = 8042645594070245259L;

    public abstract String roundType();
    public abstract int getMaxRoundNumber();

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;

    public int getNumber() {
        return number;
    }

    public void setGroups(List<InterviewGroup> groups) {
        if(this.groups != null){
            throw new RuntimeException("You have already assigned groups!!!");
        }
        this.groups = groups;
    }

    public List<InterviewGroup> getGroups() {
        return groups;
    }

    private List<InterviewGroup> groups;

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    private Interview interview = null;

    private boolean isAllGroupsSubmitted(){
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

    public List<String> getApplicantsNamePassed(){
        List<String> names = new ArrayList<>();
        for(InterviewGroup group: groups){
            names.addAll(group.getApplicantsNamePassed());
        }
        return names;
    }

    public Round(int number){
        if(number > getMaxRoundNumber()){
            throw new RuntimeException("No you cannot add more rounds !!!");
        }
        this.number = number;
    }
}
