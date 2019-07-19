package project.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import project.user.Applicant;
import project.user.ApplicantHistory;
import project.utils.Logging;

import java.io.IOException;
import java.util.logging.Logger;

public class SignUpController extends ApplicationController{

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
        boolean added = false;
        if (UserTypes.typeName.equals("Applicant")){
            added = getSystem().addUser(new Applicant(new ApplicantHistory(getSystem().now()),
                    usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), null));
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
