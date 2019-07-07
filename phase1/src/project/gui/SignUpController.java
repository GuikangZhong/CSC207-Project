package project.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import project.user.Applicant;
import project.user.ApplicantHistory;

import java.io.IOException;

public class SignUpController extends ApplicationController{

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField realNameInput;

    public void signUpButton(ActionEvent event) throws IOException {
        boolean added = false;
        if (TypeController.typeName.equals("Applicant")){
            added = getSystem().addUser(new Applicant(new ApplicantHistory(getSystem().now()),
                    usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null));
        }
        if (added){
            SceneSwitcher.switchScene(this, event, "SignUpSuccess.fxml");
        }
        else
            System.out.println("Username already exists");
    }

    public void returnToTypeButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Type.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
