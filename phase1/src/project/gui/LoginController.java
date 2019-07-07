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
        Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene mainScene = new Scene(main);
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginWindow.setScene(mainScene);
        loginWindow.show();
    }

    public void loginButton(ActionEvent event) throws IOException {
        boolean correct = false;
        User user = getSystem().getUser(usernameInput.getText());

        if (user != null){
            correct = user.verifyPassword(passwordInput.getText());
        }

        if (user == null || !correct){
            Stage window = new Stage();

            //Block events to other windows
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Warning");
            window.setHeight(100.0);
            window.setWidth(300.0);

            Label label = new Label("Username or password does not exist");
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);

            //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        }
        else if (user.getType() == User.Type.APPLICANT){
            setUser(user);
            Menu menu = new Menu();
            menu.addOption("Documents","Document.fxml")
            .addOption("Dashboard","ApplicantMenu.fxml")
            .addOption("Document","ViewJobPosting.fxml")
            .addOption("Job posting","ViewJobPosting.fxml")
            .addOption("Application","Application.fxml")
            .addOption("Your history","ApplicantHistory.fxml");
            setMenu(menu);
            SceneSwitcher.switchScene(this, event, "ApplicantMenu.fxml");
        }
        else if (user.getType() == User.Type.HR){
            setUser(user);
            Menu menu = new Menu();
            menu.addOption("View all applicants","AllApplicants.fxml")
                    .addOption("View applicants for a job posting","PostingApplicants.fxml")
                    .addOption("Inspect interviewers","Miscellaneous.fxml")
                    .addOption("Create Job Posting","CreateJobPos.fxml");
            setMenu(menu);
            SceneSwitcher.switchScene(this, event, "HRMenu.fxml");
        }
        else if (user.getType() == User.Type.INTERVIEWER){
            setUser(user);
            Menu menu = new Menu();
            menu.addOption("View all applicants","Document.fxml")
                    .addOption("Dashboard","ApplicantMenu.fxml")
                    .addOption("Document","ViewJobPosting.fxml")
                    .addOption("Job posting","ViewJobPosting.fxml")
                    .addOption("Application","Application.fxml")
                    .addOption("Your history","ApplicantHistory.fxml");
            setMenu(menu);
            SceneSwitcher.switchScene(this, event, "InterviewerMenu.fxml");
        }

    }
}
