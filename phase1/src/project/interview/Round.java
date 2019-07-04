package project.interview;

import java.util.List;

public abstract class Round {
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

}
