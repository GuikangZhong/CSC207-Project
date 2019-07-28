package project.gui;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.user.User;

import java.io.IOException;

public class LoginController extends ApplicationController {

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
                        .addOption("Your history", "ApplicantViewHistory.fxml")
                        .addOption("Notifications", "ApplicantViewNotifications.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "ApplicantViewNotifications.fxml");
            } else if (user.getType() == User.Type.HR) {
                setUser(user);
                if (user.getCompanies().size() > 1){
                    promptChooseCompany();
                }
                else{
                    user.setSignedInCompany(user.getCompanies().get(0));
                }
                Menu menu = new Menu();
                menu.addOption("View all applicants", "HRViewAllApplicants.fxml")
                        .addOption("Job Postings", "HRSeeApplicantsForJobPostings.fxml")
                        .addOption("Create Job Posting", "HRCreateJobPosting.fxml")
                        .addOption("Interview Assignment", "HRSeeScheduledInterviews.fxml")
                        .addOption("Final Candidates", "HRSeeFinalCandidates.fxml")
                        .addOption("Set format for job postings", "HRSetInterviewFormats.fxml")
                        .addOption("New interview format", "HRCreateInterviewFormat.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
            } else if (user.getType() == User.Type.INTERVIEWER) {
                setUser(user);
                user.setSignedInCompany(user.getCompanies().get(0));
                Menu menu = new Menu();
                menu.addOption("View all applicants", "InterviewerSeeInterviewGroups.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "InterviewerSeeInterviewGroups.fxml");
            }
        }
    }

    private void promptChooseCompany(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose a company to sign in");
        ComboBox<String> companies = new ComboBox<>();
        for (String company: user.getCompanies())
            companies.getItems().add(company);
        Button okButton = new Button("OK");
//        okButton.setOnAction(e -> {user.setSignedInCompany(companies.getSelectionModel().getSelectedItem()));
        okButton.setOnAction(e -> {
            user.setSignedInCompany(companies.getSelectionModel().getSelectedItem());
            stage.close();
        });
        HBox hBox = new HBox();
        hBox.getChildren().add(companies);
        hBox.setAlignment(Pos.CENTER);
        VBox layout = new VBox();
        layout.getChildren().addAll(hBox, okButton);
        Scene stageScene = new Scene(layout, 300, 300);
        stage.setScene(stageScene);
        stage.showAndWait();
    }
}
