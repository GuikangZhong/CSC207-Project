package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import project.application.Company;
import project.interview.InterviewSetup;
import project.interview.RoundFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class HRCreateInterviewFormat extends ApplicationController {
    private ArrayList<String> interviewFormats =
            new ArrayList<>(Arrays.asList(
                    "Take-home test",
                    "In-person Interview",
                    "Phone Interview",
                    "Group Interview"
            ));

    @FXML
    private ListView<String> availableTypes;

    @FXML
    private ListView<String> typesSelected;

    @FXML
    private TextField formatName;

    @FXML
    private Label companyName;

    @Override
    void postInit() {
        super.postInit();
        companyName.setText(getUser().getSignedInCompany());
        availableTypes.getItems().addAll(interviewFormats);
        availableTypes.setOnMouseClicked((MouseEvent event) -> addItemToAnother(event, availableTypes, typesSelected));
        typesSelected.setOnMouseClicked((MouseEvent event) -> removeByClick(event, typesSelected));
    }

    private void addItemToAnother(MouseEvent event, ListView src, ListView dst) {
        if (event.getClickCount() == 2) {
            String typeSelected = (String) src.getSelectionModel().getSelectedItem();
            dst.getItems().add(typeSelected);
        }
    }

    private void removeByClick(MouseEvent event, ListView clicked){
        if (event.getClickCount() == 2){
            int selected = clicked.getSelectionModel().getSelectedIndex();
            clicked.getItems().remove(selected);
        }
    }

    public void returnClicked(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "HRSeeApplicantsForJobPostings.fxml");
    }

    public void submitClicked(ActionEvent event){
        InterviewSetup format = new InterviewSetup();
        RoundFactory factory = new RoundFactory();
        String name = formatName.getText();
        for (String selectedType: typesSelected.getItems()){
            format.addRound(factory.createRound(selectedType));
        }
        try{
            Company company = getSystem().getCompany((getUser()).getSignedInCompany());
            company.addInterviewFormat(name, format);
            showModal("Successfully added.");
        }
        catch (Exception e){
            showModal("Warning", "Cannot add the format.");
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}


