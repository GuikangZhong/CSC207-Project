package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.application.Company;
import project.user.Applicant;
import project.user.HR;

import java.io.IOException;
import java.util.HashMap;

public class HRViewTags extends ApplicationController{
    @FXML
    private TextField newTag;
    @FXML
    private ListView<String> tags;
    @FXML
    private Label companyName;

    private String oldTag;

    private Company company = getSystem().getCompany(companyName.getText());

    @Override
    void postInit(){
        super.postInit();
        initializeTags();
        tags.setCellFactory(CellFactoryFactory.getCellFactoryForTag());
    }

    private void initializeTags(){
        this.tags.getItems().addAll(company.getTags());
    }

//    private void pullTags() {
//        HR hr = (HR) getUser();
//        Company company = getSystem().getCompany(hr.getSignedInCompany());
//        tags.getItems().clear();
//        tags.getItems().addAll(company.getTags());
//
//    }

    public void addTag(ActionEvent event){
        Company company = getSystem().getCompany(companyName.getText());
        company.addTag(newTag.getText());
        tags.getItems().add(newTag.getText());
    }

//    public void tagClicked(MouseEvent event){
//
//    }

    public void removeTag(ActionEvent event){
        Company company = getSystem().getCompany(companyName.getText());

    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
