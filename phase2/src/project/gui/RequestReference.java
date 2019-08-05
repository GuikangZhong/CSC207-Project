package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.application.JobPosting;
import project.user.Applicant;
import project.user.Referee;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
import java.util.Map;

public class RequestReference extends ApplicationController implements Serializable {

    private static final long serialVersionUID = -3896581029543183123L;
    @FXML
    ListView<Referee> referees;
    private Referee selectedReferee;
    @FXML
    ListView<JobPosting> jobPostings;
    private JobPosting selectedPosting;

    void postInit(){
        super.postInit();
        referees.setCellFactory(new Callback<ListView<Referee>, ListCell<Referee>>() {

            @Override
            public ListCell<Referee> call(ListView<Referee> p) {

                ListCell<Referee> cell = new ListCell<Referee>() {

                    @Override
                    protected void updateItem(Referee t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getUsername());
                        } else {
                            setText("");
                        }
                    }

                };
                return cell;
            }
        });
        jobPostings.setCellFactory(CellFactoryFactory.getCellFactoryForJobPosting());
        pollReferees();
        pollJobPostings();
    }

    private void pollReferees(){
        HashMap<String, Referee> allReferees = getSystem().getReferees();
        for (Map.Entry mapElement : allReferees.entrySet()) {
            referees.getItems().add((Referee) mapElement.getValue());
        }
    }

    private void pollJobPostings(){
        List<JobPosting> jobPostingList = getSystem().getAllJobPostings();
        for (JobPosting jobPosting: jobPostingList) {
            jobPostings.getItems().add(jobPosting);
        }
    }

    public void refereeClicked(MouseEvent event) {
        selectedReferee = referees.getSelectionModel().getSelectedItem();
    }

    public void jobPostingClicked(MouseEvent event) {
        selectedPosting = jobPostings.getSelectionModel().getSelectedItem();
    }

    public void requestButton(ActionEvent event) throws IOException {
        if (selectedReferee == null) {
            showModal("Warning", "referee not selected");
        } else if (selectedPosting == null) {
            showModal("Warning", "job posting not selected");
        } else {
            selectedReferee.addRequest((Applicant) getUser(), selectedPosting);
            showModal("Successfully requested");
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}