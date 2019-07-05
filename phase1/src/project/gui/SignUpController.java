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
        if (TypeController.typeName.equals("Applicant")){
            Main.system.addUser(new Applicant(null, usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null));
        }
        else if (TypeController.typeName.equals("HR")){
            Main.system.addUser(new Applicant(null, usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null));
        }
        else if (TypeController.typeName.equals("Interviewer")){
            Main.system.addUser(new Applicant(null, usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null));
        }
        System.out.println(TypeController.typeName);
        SceneSwitcher.switchScene(this.getClass(), event, "SignUpSuccess.fxml");
    }

    public void returnToTypeButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "Type.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
