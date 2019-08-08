package project.gui.HRGUIs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import project.application.Company;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;

import java.io.IOException;

public class SwitchCompany extends ApplicationController {
    @FXML
    private ChoiceBox<String> companies;

    @Override
    public void postInit() {
        super.postInit();
        for (Company company: user.getCompanies()){
            companies.getItems().add(company.getName());
        }
    }

    public void confirmClicked(ActionEvent event) throws IOException {
//        String companyName = companies.getSelectionModel().getSelectedItem();
        Company signedInCompany = system.getCompany(companies.getSelectionModel().getSelectedItem());
        user.setSignedInCompany(signedInCompany);
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }

    public void returnClicked(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }
}
