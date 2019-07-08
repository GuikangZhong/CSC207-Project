package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.util.Callback;
import project.application.Application;
import project.application.Company;
import project.application.JobPosting;
import project.application.JobPostingManager;
import project.interview.Interview;
import project.interview.InterviewGroup;
import project.user.Applicant;
import project.user.User;
import project.user.Interviewer;
import project.user.InterviewerManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class AssignInterviewsController extends ApplicationController {

    @FXML
    private ListView<Applicant> applicants = new ListView<>();
    @FXML
    private ListView<Interviewer> interviewers = new ListView<>();
    @FXML
    private ListView<Applicant> selectedApplicants = new ListView<>();
    @FXML
    private ListView<Interviewer> selectedInterviewers = new ListView<>();

    static JobPosting jobPosting = null;


    @Override
    void postInit() {
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
        pollApplicants();

        interviewers.setCellFactory(new Callback<ListView<Interviewer>, ListCell<Interviewer>>() {

            @Override
            public ListCell<Interviewer> call(ListView<Interviewer> p) {

                ListCell<Interviewer> cell = new ListCell<Interviewer>() {

                    @Override
                    protected void updateItem(Interviewer t, boolean bln) {
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
        pollInterviewers();

        selectedApplicants.setCellFactory(new Callback<ListView<Applicant>, ListCell<Applicant>>() {
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

        selectedInterviewers.setCellFactory(new Callback<ListView<Interviewer>, ListCell<Interviewer>>() {
            @Override
            public ListCell<Interviewer> call(ListView<Interviewer> p) {

                ListCell<Interviewer> cell = new ListCell<Interviewer>() {

                    @Override
                    protected void updateItem(Interviewer t, boolean bln) {
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

        // Add mouse event handlers for the source
        applicants.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, applicants);
        });
        applicants.setOnDragDone((DragEvent event) -> {
            dragDone(event, applicants);
        });
        interviewers.setOnDragDetected((MouseEvent event) -> {
            dragDetected(event, interviewers);
        });
        selectedApplicants.setOnDragOver((DragEvent event) -> {
            dragOver(event, selectedApplicants);
        });
        selectedApplicants.setOnDragDropped((DragEvent event) -> {
            dragApplicantDropped(event, selectedApplicants);
        });
        selectedInterviewers.setOnDragOver((DragEvent event) -> {
            dragOver(event, selectedInterviewers);
        });
        selectedInterviewers.setOnDragDropped((DragEvent event) -> {
            dragInterviewerDropped(event, selectedInterviewers);
        });

    }

    private void pollApplicants() {
        if (jobPosting != null) {
            for (Application application : jobPosting.getApplications()) {
                applicants.getItems().add(application.getApplicant());
            }
        }
    }

    private void pollInterviewers() {
        if (getUser() != null) {
            String companyName = getUser().getCompany();
            Company company = getSystem().getCompany(companyName);
            InterviewerManager interviewerManager = company.getInterviewerManager();
            for (Interviewer interviewer : interviewerManager.getUsers().values()) {
                interviewers.getItems().add(interviewer);
            }
        }
    }

    public void submitButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    private void dragDetected(MouseEvent event, ListView listView) {
        // Make sure at least one item is selected
        int selectedCount = listView.getSelectionModel().getSelectedIndices().size();

        if (selectedCount == 0) {
            event.consume();
            return;
        }

        Dragboard db = listView.startDragAndDrop(TransferMode.MOVE);

        // Put a string on a dragboard as an identifier
        ClipboardContent content = new ClipboardContent();
        content.putString(listView.getId());
        db.setContent(content);

        //Consume the event
        event.consume();
    }

    private void dragOver(DragEvent event, ListView listView) {
        // If drag board has an ITEM_LIST and it is not being dragged
        // over itself, we accept the MOVE transfer mode
        Dragboard dragboard = event.getDragboard();

        if (event.getGestureSource() != listView && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    @SuppressWarnings("unchecked")
    private void dragApplicantDropped(DragEvent event, ListView listView) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        //Could have some more thorough checks of course.
        if (db.hasString()) {
            //Get the textarea and place it into flowPane2 instead
            listView.getItems().add(applicants.getSelectionModel().getSelectedItem());
            success = true;
        }
        //Complete and consume the event.
        event.setDropCompleted(success);
        event.consume();
    }

    @SuppressWarnings("unchecked")
    private void dragInterviewerDropped(DragEvent event, ListView listView) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        //Could have some more thorough checks of course.
        if (db.hasString()) {
            //Get the textarea and place it into flowPane2 instead
            listView.getItems().add(interviewers.getSelectionModel().getSelectedItem());
            success = true;
        }
        //Complete and consume the event.
        event.setDropCompleted(success);
        event.consume();
    }

    private void dragDone(DragEvent event, ListView listView) {
        // Check how data was transfered to the target
        // If it was moved, clear the selected items
        TransferMode tm = event.getTransferMode();
        if (tm == TransferMode.MOVE) {
            removeApplicant(listView);
        }
        event.consume();
    }

    private void removeApplicant(ListView<Applicant> listView) {
        Applicant applicant = listView.getSelectionModel().getSelectedItem();
        // Clear the selection
        listView.getSelectionModel().clearSelection();
        // Remove items from the selected list
        listView.getItems().remove(applicant);
    }
}