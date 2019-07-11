package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import project.user.Applicant;
import project.user.HR;
import project.user.Interviewer;
import project.user.User;

import java.io.IOException;

public class LoginController extends ApplicationController{

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    public void loginButton(ActionEvent event) throws IOException {
        boolean correct = false;
        User user = getSystem().getUser(usernameInput.getText());

        if (user != null){
            correct = user.verifyPassword(passwordInput.getText());
        }

        if (user == null || !correct){
            showModal("Username or password does not exist");
        }
        else {
            getSystem().notifyOnTimeUpdate();
            if (user.getType() == User.Type.APPLICANT) {
                setUser(user);
                Menu menu = new Menu();
                menu.addOption("Documents", "ApplicantViewOrUpdateDocument.fxml")
                        .addOption("Job Posting", "ApplicantViewJobPostings.fxml")
                        .addOption("Application", "ApplicantViewApplications.fxml")
                        .addOption("Your history", "ApplicantViewHistory.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "ApplicantViewJobPostings.fxml");
            } else if (user.getType() == User.Type.HR) {
                setUser(user);
                Menu menu = new Menu();
                menu.addOption("View all applicants", "HRViewAllApplicants.fxml")
                        .addOption("Job Postings", "HRSeeApplicantsForJobPostings.fxml")
                        .addOption("Create Job Posting", "HRCreateJobPosting.fxml")
                        .addOption("Interview Assignment", "HRSeeScheduledInterviews.fxml")
                        .addOption("Recommendation list", "HRSeeFinalCandidates.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "HRViewAllApplicants.fxml");
            } else if (user.getType() == User.Type.INTERVIEWER) {
                setUser(user);
                Menu menu = new Menu();
                menu.addOption("View all applicants", "InterviewerSeeInterviewGroups.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "InterviewerSeeInterviewGroups.fxml");
            }
        }
    }
}
