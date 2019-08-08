package project.gui.GeneralUseGUIs;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.application.Company;
import project.user.User;

import java.io.IOException;

public class LoginController extends ApplicationController {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
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
                menu.addOption("Documents", "../ApplicantGUIs/ApplicantViewOrUpdateDocument.fxml")
                        .addOption("Job Posting", "../ApplicantGUIs/ApplicantViewJobPostings.fxml")
                        .addOption("Application", "../ApplicantGUIs/ApplicantViewApplications.fxml")
                        .addOption("Your history", "../ApplicantGUIs/ApplicantViewHistory.fxml")
                        .addOption("Notifications", "../ApplicantGUIs/ApplicantViewNotifications.fxml")
                        .addOption("Reference", "../ApplicantGUIs/RequestReference.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "../ApplicantGUIs/ApplicantViewNotifications.fxml");
            } else if (user.getType() == User.Type.HR) {
                setUser(user);
                if (user.getCompanies().size() > 1){
                    promptChooseCompany();
                }
                else{
                    user.setSignedInCompany(user.getCompanies().get(0));
                }
                Menu menu = new Menu();
                menu.addOption("View all applicants", "../HRGUIs/HRViewAllApplicants.fxml")
                        .addOption("Job Postings", "../HRGUIs/HRSeeApplicantsForJobPostings.fxml")
                        .addOption("Create Job Posting", "../HRGUIs/HRCreateJobPosting.fxml")
                        .addOption("Interview Assignment", "../HRGUIs/HRSeeScheduledInterviews.fxml")
                        .addOption("Final Candidates", "../HRGUIs/HRSeeFinalCandidates.fxml")
                        .addOption("Set format for job postings", "../HRGUIs/HRSetInterviewFormats.fxml")
                        .addOption("New interview format", "../HRGUIs/HRCreateInterviewFormat.fxml")
                        .addOption("Join new subsidiaries", "../HRGUIs/JoinSubsidiary.fxml")
                        .addOption("Switch Company", "../HRGUIs/SwitchCompany.fxml")
                        .addOption("View Tags", "../HRGUIs/HRViewTags.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "../HRGUIs/HRSetInterviewFormats.fxml");
            } else if (user.getType() == User.Type.INTERVIEWER) {
                setUser(user);
                user.setSignedInCompany(user.getCompanies().get(0));
                Menu menu = new Menu();
                menu.addOption("View all applicants", "../OtherUserGUIs/InterviewerSeeInterviewGroups.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "../OtherUserGUIs/InterviewerSeeInterviewGroups.fxml");
            } else if (user.getType() == User.Type.REFEREE) {
                setUser(user);
                Menu menu = new Menu();
                menu.addOption("Upload RL", "../OtherUserGUIs/RefereeMenu.fxml");
                setMenu(menu);
                SceneSwitcher.switchScene(this, event, "../OtherUserGUIs/RefereeMenu.fxml");
            }
        }
    }

    private void promptChooseCompany(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose a company to sign in");
        ComboBox<String> companies = new ComboBox<>();
        for (Company company: user.getCompanies())
            companies.getItems().add(company.getName());
        Button okButton = new Button("OK");
//        okButton.setOnAction(e -> {user.setSignedInCompany(companies.getSelectionModel().getSelectedItem()));
        okButton.setOnAction(e -> {
            user.setSignedInCompany(system.getCompany(companies.getSelectionModel().getSelectedItem()));
            stage.close();
        });
        companies.setPrefWidth(250);
        okButton.setPrefWidth(100);
        HBox hBox1 = new HBox();
        hBox1.getChildren().add(companies);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.getChildren().add(okButton);
        hBox2.setAlignment(Pos.CENTER);
        VBox layout = new VBox();
        layout.setSpacing(50);
        layout.getChildren().addAll(hBox1, hBox2);
        Scene stageScene = new Scene(layout, 300, 100);
        stage.setScene(stageScene);
        stage.showAndWait();
    }
}
