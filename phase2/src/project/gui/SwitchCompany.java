package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class SwitchCompany extends ApplicationController{
    @FXML
    private ChoiceBox<String> companies;

    @Override
    void postInit() {
        super.postInit();
        for (String companyName: user.getCompanies()){
            companies.getItems().add(companyName);
        }
    }

    public void comfirmClicked(ActionEvent event) throws IOException {
        String companyName = companies.getSelectionModel().getSelectedItem();
        user.setSignedInCompany(companyName);
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }

    public void returnClicked(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "HRSetInterviewFormats.fxml");
    }
}
