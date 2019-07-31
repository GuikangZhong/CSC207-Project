package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import project.application.Application;
import project.application.JobPosting;

import java.io.IOException;
import java.io.Serializable;

public class RefereeMenu extends HRSeeApplicantsForJobPostings implements Serializable {
    private static final long serialVersionUID = -5054910626456091917L;

    @FXML
    ListView<JobPosting> jobPostings;
    @FXML
    ListView<Application> applications;

    @Override
    void postInit() {
        super.postInit();
    }

    public void uploadButton(ActionEvent event) throws IOException{

    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
