package project.gui;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import project.application.*;
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
    private ChoiceBox<String> jobTag;

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
    private Label companyName;





    @Override
    void postInit(){
        super.postInit();
        companyName.setText(getUser().getSignedInCompany());
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
    static private Logger logger = Logging.getLogger();
    public void submitJobPos(ActionEvent event) throws IOException {
        String title1 = title.getText();
        Integer openYear = openedYear.getSelectionModel().getSelectedItem();
        Integer openMonth = openedMonth.getSelectionModel().getSelectedItem();
        Integer openDay = openedDay.getSelectionModel().getSelectedItem();
        Integer closeYear = closedYear.getSelectionModel().getSelectedItem();
        Integer closeMonth = closedMonth.getSelectionModel().getSelectedItem();
        Integer closeDay = closedDay.getSelectionModel().getSelectedItem();
        String tag = jobTag.getSelectionModel().getSelectedItem();
        List<String> tags = new ArrayList<>();
        tags.add(tag);

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
            Job job = new Job(title1, company);
            Requirement requirement = new BasicRequirement();
            JobPosting jobPosting = new JobPosting(((HR)getUser()), job, openTime,
                    closeTime, requirement, Integer.parseInt(numOpen), description_, tags);
            JobPostingManager jobPostingManager = company.getJobPostingManager();
//            InterviewSetup format = promptInterviewFormat(company, job);
            if (jobPostingManager.addJobPosting(jobPosting)) {
                logger.info("job added successfully");
                showModal("Great","job added successfully");
            }
        }else{
            showModal("Cannot create job post.");
        }
    }

//    private InterviewSetup promptInterviewFormat(Company company, Job job){
//        Stage stage = new Stage();
//        InterviewSetup format = new InterviewSetup();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setTitle("Set the interview format");
//        Button selectButton = new Button("Select format");
//        Button createButton = new Button("New format");
//        selectButton.setOnAction(e -> {
//            selectFormat(format, company);
//            stage.close();
//        });
//        createButton.setOnAction(e -> {
//            createFormat(format, company);
//            stage.close();
//                }
//        );
//        HBox hBox = new HBox();
//        hBox.getChildren().addAll(selectButton, createButton);
//        VBox layout = new VBox();
//        layout.getChildren().add(hBox);
//        Scene stageScene = new Scene(layout, 300, 300);
//        stage.setScene(stageScene);
//        stage.showAndWait();
//        return format.createSetupWithJob(job);
//    }

//    private void selectFormat(InterviewSetup format, Company company){
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setTitle("Choose an interview format");
//        ListView<String> availableFormats = new ListView<>();
//        for (String formatName: company.getFormatNames()){
//            availableFormats.getItems().add(formatName);
//        }
//
//    }
//
//    private void createFormat(InterviewSetup format, Company company){
//
//    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
