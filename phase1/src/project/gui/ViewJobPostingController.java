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

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class ViewJobPostingController extends ApplicationController implements Initializable{
    @FXML
    private ListView<JobPosting> jobList;
    @FXML
    private TextArea description;

    @FXML
    private Label nNeeded;

    @FXML
    private Label openDays;

    @Override
    void postInit(){
        super.postInit();
        // fill the document list
        List<JobPosting> jobPostings = getSystem().getAllJobPostings();
        if (jobPostings.size() != 0) {
            for (JobPosting jp : jobPostings){
                jobList.getItems().add(jp);
            }
        }
    }

    public void selectJobPosting(MouseEvent event) throws IOException {
        JobPosting jobPosting = jobList.getSelectionModel().getSelectedItem();
        if (jobPosting != null) {
            description.setText(jobPosting.getDescription());
        }
        String pattern = "MM/dd/yyyy HH:mm:ss";


        DateFormat df = new SimpleDateFormat(pattern);
        openDays.setText(String.format("From %s to %s", jobPosting.getOpenDate().toLocalDate(),
                jobPosting.getCloseDate().toLocalDate()));
        nNeeded.setText(Integer.valueOf(jobPosting.getnApplicantNeeded()).toString());
    }

    public void applyButton(ActionEvent event){
        JobPosting jobPosting = jobList.getSelectionModel().getSelectedItem();
        Applicant applicant = (Applicant)getUser();
        Application application = applicant.apply(jobPosting);
        if(application == null){
            showModal("Cannot apply");
        }else{
            System.out.println("Applied");
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

