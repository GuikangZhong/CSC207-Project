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
        else if (user.getType() == User.Type.APPLICANT){
            setUser(user);
            Menu menu = new Menu();
            menu.addOption("Documents","Document.fxml")
            .addOption("Dashboard","ApplicantMenu.fxml")
            .addOption("Job Posting","ViewJobPosting.fxml")
            .addOption("Application","Application.fxml")
            .addOption("Your history","ApplicantHistory.fxml");
            setMenu(menu);
            SceneSwitcher.switchScene(this, event, "ApplicantMenu.fxml");
        }
        else if (user.getType() == User.Type.HR){
            setUser(user);
            Menu menu = new Menu();
            menu.addOption("View all applicants","AllApplicants.fxml")
                    .addOption("Assign interviews","PostingApplicants.fxml")
                    .addOption("Create Job Posting","CreateJobPost.fxml");
            setMenu(menu);
            SceneSwitcher.switchScene(this, event, "HRMenu.fxml");
        }
        else if (user.getType() == User.Type.INTERVIEWER){
            setUser(user);
            Menu menu = new Menu();
            menu.addOption("View all applicants","InterviewGroups.fxml")
                    .addOption("Your history","ApplicantHistory.fxml");
            setMenu(menu);
            SceneSwitcher.switchScene(this, event, "InterviewerMenu.fxml");
        }

    }
}
