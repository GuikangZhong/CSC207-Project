package project.gui.GeneralUseGUIs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class UserTypes extends ApplicationController implements Initializable {

    @FXML
    ChoiceBox<String> type;

    static String typeName;

    @Override
    public void postInit(){
        super.postInit();
        type.getItems().addAll("Applicant", "HR", "Interviewer", "Referee");
    }
    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        if (typeName != null){
            if (typeName.equals("Applicant") || typeName.equals("Referee"))
                SceneSwitcher.switchScene(this, event, "SignUpPage.fxml");
            else if (typeName.equals("Interviewer") || typeName.equals("HR"))
                SceneSwitcher.switchScene(this, event, "StaffSignUpPage.fxml");
        }
    }

    public void typeSelected(MouseEvent event) throws IOException {
        type.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)  -> {
            if (newValue != null){
                typeName = newValue;
            }
        });
    }
}
