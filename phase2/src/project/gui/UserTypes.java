package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class UserTypes extends ApplicationController implements Initializable {

    @FXML
    ChoiceBox<String> type;
    @FXML
    private Button confirm;

    static String typeName;

    @Override
    void postInit(){
        super.postInit();
        type.getItems().addAll("Applicant", "HR", "Interviewer");
    }
    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        if (typeName != null){
            if (typeName.equals("Applicant"))
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
