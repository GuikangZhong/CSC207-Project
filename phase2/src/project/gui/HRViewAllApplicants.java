package project.gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import project.application.Document;
import project.application.JobPosting;
import project.user.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HRViewAllApplicants extends ApplicationController {

    @FXML
    private ListView<Applicant> applicants;

    @FXML
    private ListView<String> applicantDocuments;

    @FXML
    private ListView<String> applicantApplyingJobs;

    @FXML
    private TextArea documentContent;

    private Applicant applicant;


    @Override
    void postInit(){
        super.postInit();
        initializeApplicants();
        applicants.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());
    }

    private void initializeApplicants(){
        HashMap<String, Applicant> applicants = getSystem().getApplicants();
        for (Applicant applicant: applicants.values()){
            this.applicants.getItems().add(applicant);
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
        documentContent.clear();
        applicant = applicants.getSelectionModel().getSelectedItem();
        for(Document document : applicant.getDocuments()){
            applicantDocuments.getItems().add(document.getName());
        }

        applicantApplyingJobs.getItems().clear();
        ApplicantHistory applicantHistory = applicant.getApplicantHistory();
        Set<JobPosting> jobsApplying = applicantHistory.getJobApplying();
        for (JobPosting job: jobsApplying){
            if (job.getCompany().getName().equals(getUser().getSignedInCompany())){
                applicantApplyingJobs.getItems().add(job.getJobTitle());
           }
        }
        List<JobPosting> jobsApplied = applicantHistory.getJobApplied();
        for (JobPosting job: jobsApplied){
            if (job.getCompany().getName().equals(getUser().getSignedInCompany())){
                applicantApplyingJobs.getItems().add(job.getJobTitle());
            }
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
