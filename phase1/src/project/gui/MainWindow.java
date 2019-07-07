package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow extends ApplicationController implements Initializable {
    @FXML
    private TextField companyName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loginButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "LoginPage.fxml");

    }

    public void signUpButton(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Type.fxml");
    }

    public void addCompanyButton(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "AddCompany.fxml");
    }

    public void addCompanyConfirmButton(ActionEvent event) throws IOException{
        if (companyName.getText() != null) {
            String name = companyName.getText().toLowerCase();
            boolean added = getSystem().addCompany(name);
            if (added){
                System.out.println("Successfully added");
                SceneSwitcher.switchScene(this, event, "Main.fxml");
            }
            else{
                System.out.println("Company already exist");
            }
        }
    }

    public void returnButton(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}