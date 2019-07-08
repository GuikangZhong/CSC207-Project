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

        applicants.setOnMouseClicked((MouseEvent click) -> {
            addItemToOther(click, applicants, selectedApplicants);
        });

        selectedApplicants.setOnMouseClicked((MouseEvent click) -> {
            addItemToOther(click, selectedApplicants, applicants);
        });

        interviewers.setOnMouseClicked((MouseEvent click) -> {
            addItemToOther(click, interviewers, selectedInterviewers);
        });

        selectedInterviewers.setOnMouseClicked((MouseEvent click) -> {
            addItemToOther(click, selectedInterviewers, interviewers);
        });
    }

    private void addItemToOther(MouseEvent click, ListView source, ListView destination){
        if (click.getClickCount() == 2) {
            User user = (User)source.getSelectionModel().getSelectedItem();
            destination.getItems().add(user);
            source.getItems().remove(user);
        }
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
}