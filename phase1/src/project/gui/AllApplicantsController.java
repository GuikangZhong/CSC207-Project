package project.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.application.Application;
import project.application.Document;
import project.application.Job;
import project.user.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AllApplicantsController extends ApplicationController{

    @FXML
    private ListView<Applicant> applicants;

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

        applicants.setCellFactory(new Callback<ListView<Applicant>, ListCell<Applicant>>() {

            @Override
            public ListCell<Applicant> call(ListView<Applicant> p) {

                ListCell<Applicant> cell = new ListCell<Applicant>() {

                    @Override
                    protected void updateItem(Applicant t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRealName());
                        } else {
                            setText("");
                        }
                    }

                };

                return cell;
            }
        });
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
        Set<Job> jobsApplying = applicantHistory.getJobApplying();
        for (Job job: jobsApplying){
            if (job.getCompany().getName().equals(getUser().getCompany())){
                applicantApplyingJobs.getItems().add(job.getTitle());
           }
        }
        List<Job> jobsApplied = applicantHistory.getJobApplied();
        for (Job job: jobsApplied){
            if (job.getCompany().getName().equals(getUser().getCompany())){
                applicantApplyingJobs.getItems().add(job.getTitle());
            }
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
