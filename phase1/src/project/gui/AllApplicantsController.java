package project.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import project.application.Document;
import project.application.Job;
import project.user.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AllApplicantsController extends ApplicationController{

    @FXML
    private ListView<String> applicants;

    @FXML
    private ListView<String> applicantDocuments;

    @FXML
    private ListView<String> applicantApplyingJobs;

    @FXML
    private TextArea documentContent;

    @FXML
    private Label companyName;

    private Applicant applicant;


    @Override
    void postInit(){
        super.postInit();
        initializeApplicants();
    }

    private void initializeApplicants(){
        HashMap<String, Applicant> applicants = getSystem().getApplicants();  // Potential problem??
        for(String name: applicants.keySet()){
            this.applicants.getItems().add(name);
        }
    }

    public void applicantDocumentViewClicked(MouseEvent event){
        String item = applicantDocuments.getSelectionModel().getSelectedItem();
        for(Document document : applicant.getDocuments()){
            if(document.getName().equals(item)){
                documentContent.setText(document.getContent());
                break;
            }
        }
    }

    public void applicantsViewClicked(MouseEvent event){
        applicantDocuments.getItems().clear();
        applicant = (Applicant)getSystem().getUser(applicants.getSelectionModel().getSelectedItem());
        for(Document document : applicant.getDocuments()){
            applicantDocuments.getItems().add(document.getName());
        }

        applicantApplyingJobs.getItems().clear();
        ApplicantHistory applicantHistory = applicant.getApplicantHistory();
        List<Job> jobsApplied = applicantHistory.getJobApplying();
        for (Job job: jobsApplied){
            //if (job.getCompany().getName().equals(getUser().getCompany())){
            applicantApplyingJobs.getItems().add(job.getTitle());
           // }
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
