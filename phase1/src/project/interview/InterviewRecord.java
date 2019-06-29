package project.interview;

import project.application.Application;
import project.observer.Observer;

public class InterviewRecord {
    private boolean passed;
    private Application application;

    public boolean isCurrentRoundFinished() {
        return currentRoundFinished;
    }

    public void setCurrentRoundFinished(boolean currentRoundFinished) {
        this.currentRoundFinished = currentRoundFinished;
    }

    private boolean currentRoundFinished;

    public InterviewRecord(Application application) {
        passed = false;
        this.application = application;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Application getApplication() {
        return application;
    }

}
