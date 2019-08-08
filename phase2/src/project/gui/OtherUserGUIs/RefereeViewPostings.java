package project.gui.OtherUserGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import project.application.JobPosting;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;

import java.io.IOException;

public class RefereeViewPostings extends ApplicationController {

    @FXML
    Label openDays;
    private static JobPosting jobPosting;

    public void postInit() {
        openDays.setText(String.format(" %s to %s", jobPosting.getOpenDate().toLocalDate(),
                jobPosting.getCloseDate().toLocalDate()));
    }

    public static void setJobPosting(JobPosting jobPosting1) {
        jobPosting = jobPosting1;
    }

    public void returnButton(ActionEvent event) throws IOException {
        jobPosting = null;
        SceneSwitcher.switchScene(this, event, "RefereeMenu.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
    }
}