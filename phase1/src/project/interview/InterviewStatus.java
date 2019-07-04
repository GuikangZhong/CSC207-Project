package project.interview;

public class InterviewStatus {
    public int getNumber() {
        return number;
    }

    public Round getRound() {
        return round;
    }

    private Round round;
    private int number;

    public InterviewStatus(Round round, int n) {
        this.round = round;
        this.number = n;
    }


}
