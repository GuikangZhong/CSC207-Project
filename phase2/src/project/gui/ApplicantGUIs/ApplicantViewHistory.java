package project.gui.ApplicantGUIs;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import project.application.JobPosting;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.user.Applicant;
import project.user.ApplicantHistory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class ApplicantViewHistory extends ApplicationController {
    @FXML
    private ListView<String> applyingJobs, appliedJobs;

    @FXML
    private Label createDate;

    @FXML
    private Label realname;

    @FXML
    private Label username;

    @FXML
    private Label lastApplicationClosed;

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
    }

    @Override
    public void postInit() {
        super.postInit();
        Applicant applicant = (Applicant) getUser();
        ApplicantHistory history = applicant.getApplicantHistory();
        appliedJobs.getItems().clear();
        applyingJobs.getItems().clear();
        for (JobPosting job : history.getJobApplied()) {
            appliedJobs.getItems().add(job.getJobTitle());
        }
        for (JobPosting job : history.getJobApplying()) {
            applyingJobs.getItems().add(job.getJobTitle());
        }
        createDate.setText(history.getDateCreated().toString());
        username.setText(applicant.getUsername());
        realname.setText(applicant.getRealName());
        if (history.getLastApplicationClosed() == null) {
            lastApplicationClosed.setText("None");
        } else {
            LocalDateTime systemTime = getSystem().now();
            LocalDateTime closedTime = history.getLastApplicationClosed();
            LocalDate systemDate = systemTime.toLocalDate();
            LocalDate closedDate = closedTime.toLocalDate();
            lastApplicationClosed.setText(
                    String.format("%s (%d days)", closedDate,
                            DAYS.between(closedDate,systemDate))
            );
        }
    }
}
