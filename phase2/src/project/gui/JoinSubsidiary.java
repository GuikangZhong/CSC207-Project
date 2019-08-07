package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import project.application.Company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoinSubsidiary extends ApplicationController {

    @FXML
    private ChoiceBox<String> availableSubsidiaries;

    @Override
    void postInit() {
        super.postInit();
        Company currentCompany = user.getSignedInCompany();
        for (Company subsidiary : currentCompany.getSubsidiaries()) {
            if (!user.getCompanies().contains(subsidiary.getName())) {
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
        if(currentCompany.isRootCompany())
            return;
        List<Company> candidates = new ArrayList<>();
        for (Company company : system.getCompanies()) {
            if (currentCompany.isSiblingCompany(company)) {
                candidates.add(company);
            }
        }
        for(Company company:candidates){
            if(company != currentCompany){
                if(!availableSubsidiaries.getItems().contains(company.getName())){
                    availableSubsidiaries.getItems().add(company.getName());
                }
            }
        }
    }
}
