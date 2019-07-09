package project.observer;

import project.application.Application;

import java.io.Serializable;

public interface ApplicantObserver extends Serializable {
    void updateOnApplicationWithdraw(Application application);
}
