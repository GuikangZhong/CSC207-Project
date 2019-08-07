package project.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import project.user.Applicant;
import project.user.ApplicantHistory;
import project.user.Referee;
import project.user.UserFactory;
import project.utils.Logging;

import java.io.IOException;
import java.util.logging.Logger;

public class SignUpController extends ApplicationController {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField realNameInput;

    @Override
    void postInit(){
        super.postInit();
        assert system != null;
    }
    static private Logger logger = Logging.getLogger();
    public void signUpButton(ActionEvent event) throws IOException {
        boolean added;
        UserFactory factory = new UserFactory();
        if (UserTypes.typeName.equals("Applicant")){
            added = getSystem().addUser(factory.createUser(new ApplicantHistory(getSystem().now()),
                    usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null, "Applicant"));
        } else {
            added = getSystem().addUser(factory.createUser(
                    usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null, "Referee"));
        }
        if (added){
            SceneSwitcher.switchScene(this, event, "SignUpSuccess.fxml");
        }
        else
            logger.warning("Username already exists");
    }

    public void returnToTypeButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "UserTypes.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
