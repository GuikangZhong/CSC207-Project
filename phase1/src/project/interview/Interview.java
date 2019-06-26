package project.interview;

public abstract class Interview {
    public int getMaxInterview() {
        return maxInterview;
    }

    private final int maxInterview;
    Interview(int maximumInterview){
        this.maxInterview = maximumInterview;
    }
    public abstract String getInterviewType();
}
