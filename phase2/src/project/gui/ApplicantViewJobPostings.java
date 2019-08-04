package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import project.application.Application;
import project.application.JobPosting;
import project.user.Applicant;
import project.utils.Logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class ApplicantViewJobPostings extends ApplicationController implements Initializable{
    @FXML
    private ListView<JobPosting> jobList;

    @FXML
    private TextArea description;
    @FXML
    private Label nNeeded;
    @FXML
    private Label openDays;
    @FXML
    private Label postStatus;

    @FXML
    private Label companyName;

    @FXML
    private Label tags;

    @FXML
    private ListView<JobPosting> jobList1;

    @FXML
    private TextArea description1;
    @FXML
    private Label nNeeded1;
    @FXML
    private Label openDays1;
    @FXML
    private Label postStatus1;

    @FXML
    private Label companyName1;

    @FXML
    private Label tags1;
    @FXML
    private ChoiceBox<String> tag1;
    @FXML
    private ChoiceBox<String> tag2;
    @FXML
    private ChoiceBox<String> tag3;
    @FXML
    private ChoiceBox<String> tag4;
    @FXML
    private ChoiceBox<String> tag5;


    @Override
    void postInit(){
        super.postInit();
        // fill the document list
        List<JobPosting> jobPostings = getSystem().getAllJobPostings();
        if (jobPostings.size() != 0) {
            for (JobPosting jp : jobPostings){
                jobList.getItems().add(jp);
                jobList1.getItems().add(jp);
            }
        }
        List<String> tagList = getSystem().getAllTags();
//        for (String tag: tagList
//             ) {
//            tag1.getItems().add(tag);
//            tag2.getItems().add(tag);
//            tag3.getItems().add(tag);
//            tag4.getItems().add(tag);
//            tag5.getItems().add(tag);
//        }
        tag1.getItems().addAll(tagList);
        tag2.getItems().addAll(tagList);
        tag3.getItems().addAll(tagList);
        tag4.getItems().addAll(tagList);
        tag5.getItems().addAll(tagList);
    }

    public void selectJobPosting(MouseEvent event) {
        JobPosting jobPosting = jobList.getSelectionModel().getSelectedItem();
        if (jobPosting != null) {
            JobPosting.Status status = jobPosting.getStatus();
            postStatus.setText(status.toString());
            companyName.setText(jobPosting.getCompany().getName());
            description.setText(jobPosting.getDescription());
            openDays.setText(String.format(" %s to %s", jobPosting.getOpenDate().toLocalDate(),
                    jobPosting.getCloseDate().toLocalDate()));
            nNeeded.setText(Integer.valueOf(jobPosting.getnApplicantNeeded()).toString());
            tags.setText(jobPosting.getTags());
        }
    }

    public void filter(ActionEvent event){
        List<String> chosenTags = new ArrayList<>();
        chosenTags.add(tag1.getSelectionModel().getSelectedItem());
        chosenTags.add(tag2.getSelectionModel().getSelectedItem());
        chosenTags.add(tag3.getSelectionModel().getSelectedItem());
        chosenTags.add(tag4.getSelectionModel().getSelectedItem());
        chosenTags.add(tag5.getSelectionModel().getSelectedItem());
        List<JobPosting> jobPostings = getSystem().getAllJobPostings();
        for (JobPosting jp:jobPostings
        ) {
            for (String tag:chosenTags
            ) {
                if (tag != null && !jp.getTagList().contains(tag)) jobList1.getItems().remove(jp);
            }
        }
    }

    public void selectJobPosting1(MouseEvent event) {
        JobPosting jobPosting = jobList1.getSelectionModel().getSelectedItem();
        if (jobPosting != null) {
            JobPosting.Status status = jobPosting.getStatus();
            postStatus1.setText(status.toString());
            companyName1.setText(jobPosting.getCompany().getName());
            description1.setText(jobPosting.getDescription());
            openDays1.setText(String.format(" %s to %s", jobPosting.getOpenDate().toLocalDate(),
                    jobPosting.getCloseDate().toLocalDate()));
            nNeeded1.setText(Integer.valueOf(jobPosting.getnApplicantNeeded()).toString());
            tags1.setText(jobPosting.getTags());
        }
    }

    static private Logger logger = Logging.getLogger();
    public void applyButton(ActionEvent event){
        JobPosting jobPosting = jobList.getSelectionModel().getSelectedItem();
        Applicant applicant = (Applicant)getUser();
        Application application = applicant.apply(jobPosting);
        if(application == null){
            showModal("Cannot apply");
        }else{
            logger.info(applicant.getUsername() + " applied for " + jobPosting.getJobTitle());
            showModal("Great","Applied");
        }
    }

    public void applyButton1(ActionEvent event){
        JobPosting jobPosting = jobList1.getSelectionModel().getSelectedItem();
        Applicant applicant = (Applicant)getUser();
        Application application = applicant.apply(jobPosting);
        if(application == null){
            showModal("Cannot apply");
        }else{
            logger.info(applicant.getUsername() + " applied for " + jobPosting.getJobTitle());
            showModal("Great","Applied");
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

