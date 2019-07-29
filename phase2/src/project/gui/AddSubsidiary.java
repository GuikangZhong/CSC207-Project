package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import project.application.Company;

import java.io.IOException;

public class AddSubsidiary extends ApplicationController {
    @FXML
    private ChoiceBox<String> currentCompanies;

    @FXML
    private TextField subsidiaryName;

    @Override
    void postInit() {
        super.postInit();
        for (Company company: system.getCompanies()){
            currentCompanies.getItems().add(company.getName());
        }
    }

    public void addButton(ActionEvent event) throws IOException {
        if ((currentCompanies.getSelectionModel().getSelectedItem() == null) ||
                (subsidiaryName.getText() == null)){
            return;
        }
        Company parentCompany = system.getCompany(currentCompanies.getSelectionModel().getSelectedItem());
        String subsidiary = subsidiaryName.getText().toLowerCase();
        if (system.addCompany(subsidiary)){
            Company sub = system.getCompany(subsidiary);
            parentCompany.addSubsidiary(sub);
            sub.setParentCompany(parentCompany);
//            showModal("Completed", "Subsidiary added.");
            SceneSwitcher.switchScene(this, event, "Main.fxml");
        }
        else {
            showModal("Company name already exists.");
        }
    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
