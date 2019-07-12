package project.observer;

import project.application.Application;

import java.io.Serializable;

public interface ApplicantObserver extends Serializable {
    /**
     * is called when application is withdrawn
     * @param application
     */
    void updateOnApplicationWithdraw(Application application);
}
