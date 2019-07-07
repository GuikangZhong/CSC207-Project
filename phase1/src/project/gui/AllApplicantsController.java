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

public class AllApplicantsController implements Initializable {
    @FXML
    private TreeView<String> options;

    @FXML
    private ListView<String> applicants;

    @FXML
    private ListView<String> applicantDocuments;

    @FXML
    private ListView<String> applicantAppliedJobs;

    @FXML
    private TextArea documentContent;

    @FXML
    private Label companyName;

    private Applicant applicant;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        companyName.setText(Main.user.getCompany());
        options.setRoot(HRMenuController.getMenu().getOption());

        this.initializeApplicants();
    }

    private void initializeApplicants(){
        HashMap<String, Applicant> applicants = Main.system.getApplicants();  // Potential problem??
        for(String name: applicants.keySet()){
            this.applicants.getItems().add(name);
        }
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        HRMenuController.getMenu().selectItem(this.getClass(),event, item);
    }

    public void applicantDocumentViewClocked(MouseEvent event){
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
        applicant = (Applicant)Main.system.getUser(applicants.getSelectionModel().getSelectedItem());
        for(Document document : applicant.getDocuments()){
            applicantDocuments.getItems().add(document.getName());
        }

        applicantAppliedJobs.getItems().clear();
        ApplicantHistory applicantHistory = applicant.getApplicantHistory();
        List<Job> jobsApplied = applicantHistory.getJobApplied();
        for (Job job: jobsApplied){
            if (job.getCompany().getName().equals(Main.user.getCompany())){
                applicantAppliedJobs.getItems().add(job.getTitle());
            }
        }
    }



    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
