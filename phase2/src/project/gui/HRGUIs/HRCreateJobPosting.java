package project.gui.HRGUIs;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.application.*;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.user.HR;
import project.utils.Logging;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

import java.util.logging.Logger;

public class HRCreateJobPosting extends ApplicationController {

    @FXML
    private TextField title;
    @FXML
    private TextField numOpenings;
    @FXML
    private TextArea jobDescription;

    @FXML
    private ChoiceBox<Integer> openedYear;

    @FXML
    private ChoiceBox<Integer> openedMonth;

    @FXML
    private ChoiceBox<Integer> openedDay;

    @FXML
    private ChoiceBox<Integer> closedYear;

    @FXML
    private ChoiceBox<Integer> closedMonth;

    @FXML
    private ChoiceBox<Integer> closedDay;

    @FXML
    private ChoiceBox<String> requirementsAvailable;

    @FXML
    private TextField newTag;

    @FXML
    private ListView<CheckBox> availableTags;


    @FXML
    private Label companyName;


    private int numLetterRequired;
    static private Logger logger = Logging.getLogger();


    @Override
    public void postInit(){
        super.postInit();
        requirementsAvailable.getItems().addAll("Basic", "Reference");
        companyName.setText(getUser().getSignedInCompany().getName());
        List<String> tagList = user.getSignedInCompany().getTags();
        for (String tag: tagList){
            CheckBox tagOption = new CheckBox(tag);
            availableTags.getItems().add(tagOption);
        }
        for (int y = 2019; y <= 2022; y++){
            openedYear.getItems().add(y);
            closedYear.getItems().add(y);
        }
        for (int d = 1; d <= 31; d++){
            openedDay.getItems().add(d);
            closedDay.getItems().add(d);
        }
        for (int m = 1; m <= 12; m++){
            openedMonth.getItems().add(m);
            closedMonth.getItems().add(m);
        }
    }

    public void addTag(ActionEvent event){
        Company company = system.getCompany(companyName.getText());
        if (company.addTag(newTag.getText())){
            CheckBox tempBox = new CheckBox(newTag.getText());
            availableTags.getItems().add(tempBox);
            showModal("Great","Tag added successfully");
        }else {
            showModal("Tag already exists.");
        }
    }

    public void submitJobPos(ActionEvent event) throws IOException {
        String title1 = title.getText();
        Integer openYear = openedYear.getSelectionModel().getSelectedItem();
        Integer openMonth = openedMonth.getSelectionModel().getSelectedItem();
        Integer openDay = openedDay.getSelectionModel().getSelectedItem();
        Integer closeYear = closedYear.getSelectionModel().getSelectedItem();
        Integer closeMonth = closedMonth.getSelectionModel().getSelectedItem();
        Integer closeDay = closedDay.getSelectionModel().getSelectedItem();
        List<String> tags = new ArrayList<>();
        for (CheckBox tagOption: availableTags.getItems()){
            if (tagOption.isSelected()) {
                tags.add(tagOption.getText());
            }
        }

        LocalDate openDate = LocalDate.of(openYear, openMonth, openDay);
        LocalDate closedDate = LocalDate.of(closeYear, closeMonth, closeDay);

        LocalTime timeNow = getSystem().now().toLocalTime();

        LocalDateTime openTime = LocalDateTime.of(openDate, timeNow);
        LocalDateTime closeTime = LocalDateTime.of(closedDate, timeNow);
        if(openTime.isBefore(closeTime)) {

            String numOpen = numOpenings.getText();
            String description_ = jobDescription.getText();
            String comName = companyName.getText();
            Company company = getSystem().getCompany(comName);
            VerificationStrategyFactory factory = new VerificationStrategyFactory();
            VerificationStrategy requirement = factory
                    .getStrategy(requirementsAvailable
                    .getSelectionModel()
                    .getSelectedItem());
            if (requirementsAvailable.getSelectionModel().getSelectedItem().equals("Reference")){
                promptSetRefNum();
                ((ReferenceVerificationStrategy) requirement).setReferenceLettersRequired(numLetterRequired);
            }
            JobPosting jobPosting = new JobPosting(((HR)getUser()), title1, company, openTime,
                    closeTime, requirement, Integer.parseInt(numOpen), description_, tags);
            JobPostingManager jobPostingManager = company.getJobPostingManager();
            if (jobPostingManager.addJobPosting(jobPosting)) {
                logger.info("job added successfully");
                showModal("Great","job added successfully");
            }
        }else{
            showModal("Cannot create job post.");
        }
    }

    private void promptSetRefNum(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Set required number of reference letters");
        TextField numField = new TextField();
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            numLetterRequired = Integer.parseInt(numField.getText());
            stage.close();
        });
        HBox hBox = new HBox();
        hBox.getChildren().add(numField);
        hBox.setAlignment(Pos.CENTER);
        VBox layout = new VBox();
        layout.getChildren().addAll(hBox, okButton);
        Scene stageScene = new Scene(layout, 300, 300);
        stage.setScene(stageScene);
        stage.showAndWait();
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
