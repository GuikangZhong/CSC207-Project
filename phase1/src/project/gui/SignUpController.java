package project.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import project.user.Applicant;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField realNameInput;

    public void signUpButton(ActionEvent event) throws IOException {
        boolean added = false;
        if (TypeController.typeName.equals("Applicant")){
            added = Main.system.addUser(new Applicant(null, usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null));
        }
        if (added){
            SceneSwitcher.switchScene(this.getClass(), event, "SignUpSuccess.fxml");
        }
        else
            System.out.println("Username already exists");
    }

    public void returnToTypeButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "Type.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
