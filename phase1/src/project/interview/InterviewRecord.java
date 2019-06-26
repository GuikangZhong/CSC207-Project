package project.interview;

import project.application.Application;

public class InterviewRecord {
    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    private boolean passed;

    public Application getApplication() {
        return application;
    }

    private Application application;
    public InterviewRecord(Application application){
        passed = false;
        this.application = application;
    }
}
