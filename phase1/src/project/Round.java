package project;

import java.util.Collection;

public class Round {

    public int getTime() {
        return time;
    }

    private final int time;

    public int getNumOfApplicantsLeft() {
        return numOfApplicantsLeft;
    }

    public void setNumOfApplicantsLeft(int numOfApplicantsLeft) {
        this.numOfApplicantsLeft = numOfApplicantsLeft;
    }

    private int numOfApplicantsLeft;

    Round(int time){
        this.time = time;
    }
}
