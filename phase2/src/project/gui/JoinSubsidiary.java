package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import project.application.Company;

import java.io.IOException;

public class JoinSubsidiary extends ApplicationController {

    @FXML
    private ChoiceBox<String> availableSubsidiaries;

    @Override
    void postInit() {
        super.postInit();
        Company currentCompany = system.getCompany(user.getSignedInCompany());
        for (Company subsidiary : currentCompany.getSubsidiaries()) {
            if (!user.getCompanies().contains(subsidiary.getName())) {
                availableSubsidiaries.getItems().add(subsidiary.getName());
            }
        }

        for (Company company : system.getCompanies()) {
            if ((company.getParentCompany() == currentCompany.getParentCompany()) &&
                    !(availableSubsidiaries.getItems().contains(company.getName())) &&
                    (company.getParentCompany() != null) &&
                    (company != currentCompany)
            ) {
                availableSubsidiaries.getItems().add(company.getName());
            }
        }
    }

    public void comfirmButton(ActionEvent event) throws IOException {
        String subsidiaryName = availableSubsidiaries.getSelectionModel().getSelectedItem();
//        Company subsidiary = system.getCompany(subsidiaryName);
        user.addCompany(subsidiaryName);
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }

    public void returnButton(ActionEvent event) throws  IOException{
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }
}
