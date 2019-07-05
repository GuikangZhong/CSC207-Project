package project.interview;

import java.io.Serializable;

public class InterviewStatus implements Serializable {
    private static final long serialVersionUID = -3548145467435967616L;
    private Round round;
    private int number;

    public InterviewStatus(Round round, int n) {
        this.round = round;
        this.number = n;
    }

    public int getNumber() {
        return number;
    }

    public Round getRound() {
        return round;
    }
}
