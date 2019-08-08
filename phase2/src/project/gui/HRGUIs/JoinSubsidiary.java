package project.gui.HRGUIs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import project.application.Company;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;

import java.io.IOException;

public class JoinSubsidiary extends ApplicationController {

    @FXML
    private ChoiceBox<String> availableSubsidiaries;

    @Override
    public void postInit() {
        super.postInit();
        Company currentCompany = user.getSignedInCompany();
        for (Company subsidiary : currentCompany.getSubsidiaries()) {
            if (!user.getCompanies().contains(subsidiary)) {
                availableSubsidiaries.getItems().add(subsidiary.getName());
            }
        }
        initializeSiblingCompanies(currentCompany);
    }

    public void confirmButton(ActionEvent event) throws IOException {
        String subsidiaryName = availableSubsidiaries.getSelectionModel().getSelectedItem();
        user.addCompany(system.getCompany(subsidiaryName));
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }

    /**
     * to fully initialize the ChoiceBox availableSubsidiaries such that it contains the names of all the
     * companies that share the same parent company as currentCompany
     *
     * @param currentCompany: The company that the user currently signs in.
     */
    private void initializeSiblingCompanies(Company currentCompany) {
        for (Company company : currentCompany) {
            if (!availableSubsidiaries.getItems().contains(company.getName())) {
                availableSubsidiaries.getItems().add(company.getName());
            }
        }
    }
}
