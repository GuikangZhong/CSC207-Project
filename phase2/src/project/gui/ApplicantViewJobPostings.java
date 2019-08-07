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
import java.util.*;
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
    private ListView<CheckBox> availableTags;


    @Override
    void postInit(){
        super.postInit();
        List<JobPosting> jobPostings = getSystem().getAllJobPostings();
        if (jobPostings.size() != 0) {
            for (JobPosting jp : jobPostings){
                jobList.getItems().add(jp);
            }
        }
        initializeTags();
    }

    private void initializeTags(){
        Set<String> tagOptions = new HashSet<>();
        tagOptions.addAll(system.getAllTags());
        for (String tag: tagOptions){
            CheckBox tagBox = new CheckBox(tag);
            availableTags.getItems().add(tagBox);
        }
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

    public void clearAll(){
        postStatus.setText(null);
        companyName.setText(null);
        description.setText(null);
        openDays.setText(null);
        nNeeded.setText(null);
        tags.setText(null);
        jobList.getItems().clear();
    }

    public void filter(ActionEvent event){
        clearAll();
        List<String> chosenTags = new ArrayList<>();
        for (CheckBox tagBox: availableTags.getItems()){
            if (tagBox.isSelected()){
                chosenTags.add(tagBox.getText());
            }
        }
        for (JobPosting posting: system.getAllJobPostings()){
            if (posting.getTagList().containsAll(chosenTags)){
                jobList.getItems().add(posting);
            }
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


    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

