package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import project.application.Company;
import project.user.Applicant;
import project.user.HR;
import project.user.Interviewer;
import project.user.UserHistory;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class StaffSignUpController extends ApplicationController implements Initializable {

    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private TextField realNameInput;
    @FXML
    private ChoiceBox<String> companyNameInput;
    private String companyName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Collection<Company> companies = getSystem().getCompanies();
        for (Company company: companies){
            companyNameInput.getItems().add(company.getName());
        }
    }

    public void signUpButton(ActionEvent event) throws IOException {
        boolean added = false;
        if (TypeController.typeName.equals("HR")){
            added = getSystem().addUser(new HR(new UserHistory(getSystem().now()), usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), companyName));
        }
        else if (TypeController.typeName.equals("Interviewer")){
            added = getSystem().addUser(new Interviewer(new UserHistory(getSystem().now()), usernameInput.getText(),passwordInput.getText(),
                    realNameInput.getText(), companyName));
        }
        if (added){
            System.out.println(TypeController.typeName);
            SceneSwitcher.switchScene(this, event, "SignUpSuccess.fxml");
        }
        else
            System.out.println("Username already exists");
    }

    public void returnToTypeButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Type.fxml");
    }

    public void companySelected(MouseEvent event) {
        companyNameInput.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)  -> {
            if (newValue != null){
                companyName = newValue;
            }
        });
    }
}
