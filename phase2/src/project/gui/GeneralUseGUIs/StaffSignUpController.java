package project.gui.GeneralUseGUIs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import project.application.Company;
import project.user.UserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
    public void postInit() {
        Collection<Company> companies = getSystem().getCompanies();
        for (Company company: companies){
            companyNameInput.getItems().add(company.getName());
        }
    }

    public void signUpButton(ActionEvent event) throws IOException {
        boolean added = false;
        UserFactory factory = new UserFactory();
        if (UserTypes.typeName.equals("HR")){
            added = getSystem().addUser(factory.createUser(
                    usernameInput.getText(),
                    passwordInput.getText(),
                    realNameInput.getText(),
                    new ArrayList<>(Arrays.asList(system.getCompany(companyName))),
                    "HR"));
        }
        else if (UserTypes.typeName.equals("Interviewer")){
            added = getSystem().addUser(factory.createUser(
                    usernameInput.getText(),
                    passwordInput.getText(),
                    realNameInput.getText(),
                    new ArrayList<>(Arrays.asList(system.getCompany(companyName))),
                    "Interviewer"));
        }
        if (added){
            SceneSwitcher.switchScene(this, event, "SignUpSuccess.fxml");
        }
        else
            showModal("User already exists");
    }

    public void returnToTypeButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "UserTypes.fxml");
    }

    public void companySelected(MouseEvent event) {
        companyNameInput.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)  -> {
            if (newValue != null){
                companyName = newValue;
            }
        });
    }
}
