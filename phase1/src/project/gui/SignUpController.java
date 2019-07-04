package project.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField realNameInput;

    public void signUpButton(ActionEvent event) throws IOException {
        Main.system.signUpApplicant(usernameInput.getText(), passwordInput.getText(), realNameInput.getText());
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
