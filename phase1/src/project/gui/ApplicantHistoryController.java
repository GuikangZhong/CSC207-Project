package project.gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import project.application.Job;
import project.user.Applicant;
import project.user.ApplicantHistory;

import java.io.IOException;

public class ApplicantHistoryController extends ApplicationController {
    @FXML
    private ListView<String> applyingJobs, appliedJobs;

    @FXML
    private Label createDate;

    @FXML
    private Label realname;

    @FXML
    private Label username;

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    @Override
    void postInit() {
        super.postInit();
        Applicant applicant = (Applicant)getUser();
        ApplicantHistory history = applicant.getApplicantHistory();
        appliedJobs.getItems().clear();
        applyingJobs.getItems().clear();
        for(Job job:history.getJobApplied()){
            appliedJobs.getItems().add(job.getTitle());
        }
        for(Job job:history.getJobApplying()){
            applyingJobs.getItems().add(job.getTitle());
        }
        createDate.setText(history.getDateCreated().toString());
        username.setText(applicant.getUsername());
        realname.setText(applicant.getRealName());
    }

//    public void applyingjobClicked(MouseEvent event){
//        String jobTitle = applyingJobs.getSelectionModel().getSelectedItem();
//    }
}
