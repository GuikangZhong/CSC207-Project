package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import project.application.*;
import project.user.Applicant;
import project.user.Referee;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class RefereeMenu extends ApplicationController implements Serializable {
    private static final long serialVersionUID = -5054910626456091917L;

    @FXML
    ListView<Applicant> applicants;
    Applicant applicant;
    @FXML
    ListView<JobPosting> jobPostings;
    private JobPosting jobPosting;


    private FileChooser fc = new FileChooser();

    @Override
    void postInit() {
        super.postInit();
        applicants.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());
        jobPostings.setCellFactory(CellFactoryFactory.getCellFactoryForJobPosting());
        pollApplicants();
    }

    public void uploadButton(ActionEvent event) throws IOException {
        if (applicant == null) {
            showModal("No applicant selected");
            return;
        }
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Document document = DocumentFactory.createByFileName(selectedFile.getName(), selectedFile.getAbsolutePath(), getSystem().now(), "ReferenceLetter");
            if (!(applicant.addDocument(document))) {
                showModal("Cannot add document");
            } else {
                ((Referee)user).removeRequest(applicant, jobPosting);
                showModal("Successfully added");
            }
        } else {
            showModal("File not selected");
        }
    }

    private void pollJobPostings(){
        jobPostings.getItems().clear();
        List<JobPosting> jobPostingList = ((Referee)getUser()).getRequests().get(applicant);
        if (jobPostingList != null) {
            for (JobPosting jobPosting:jobPostingList){
                jobPostings.getItems().add(jobPosting);
            }
        }
    }

    private void pollApplicants() {
        for (Applicant applicant: ((Referee)getUser()).getRequests().keySet()) {
            applicants.getItems().add(applicant);
        }
    }

    public void applicantsClicked(MouseEvent event) {
        applicant = applicants.getSelectionModel().getSelectedItem();
        pollJobPostings();
    }

    public void jobPostingsClicked(MouseEvent event) throws IOException {
        jobPosting = jobPostings.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2){
            RefereeViewPostings.setJobPosting(jobPosting);
            SceneSwitcher.switchScene(this, event, "RefereeViewPostings.fxml");
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}