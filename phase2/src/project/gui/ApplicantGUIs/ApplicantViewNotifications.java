package project.gui.ApplicantGUIs;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import project.application.JobPosting;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.user.Applicant;
import project.user.ApplicantHistory;

import java.io.IOException;

public class ApplicantViewNotifications extends ApplicationController implements Initializable {
    @FXML
    private ListView<String> passed;
    @FXML
    private ListView<String> failed;
    @FXML
    private ListView<String> hired;


    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
    }

    @Override
    public void postInit(){
        super.postInit();
        Applicant applicant = (Applicant)getUser();
        ApplicantHistory history = applicant.getApplicantHistory();
        for (JobPosting rejectedApplication: history.getApplicationsRejected()){
            StringBuilder info = new StringBuilder();
            info.append(rejectedApplication.getJobTitle()).append(" at ").
                    append(rejectedApplication.getCompany().getName()).append("\n");
            failed.getItems().add(info.toString());
        }

        for (JobPosting hiredPos: history.getHiredPositions()){
            StringBuilder info = new StringBuilder();
            info.append(hiredPos.getJobTitle()).append(" at ").append(hiredPos.getCompany().getName()).append("\n");
            hired.getItems().add(info.toString());
        }

        for (JobPosting passedApplication: history.getApplicationsInProgress().keySet()){
            StringBuilder info = new StringBuilder();
            info.append(passedApplication.getJobTitle()).append(" at ").append(passedApplication.getCompany().getName())
                    .append(": ").append("Passed ").append(history.getApplicationsInProgress().get(passedApplication))
                    .append("\n");
            passed.getItems().add(info.toString());
        }
    }
}

