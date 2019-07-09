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
import project.interview.InterviewGroupAssignmentStrategy;
import project.interview.InterviewSetup;
import project.user.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AssignInterviewsController extends ApplicationController {

    @FXML
    private ListView<Applicant> applicants = new ListView<>();
    @FXML
    private ListView<Interviewer> interviewers = new ListView<>();
    @FXML
    private ListView<Applicant> selectedApplicants = new ListView<>();
    @FXML
    private ListView<Interviewer> selectedInterviewers = new ListView<>();

    List<Interview> listOfInterviews;
    Interview interview;
    Company company;


    @Override
    void postInit() {
        applicants.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());
        pollApplicants();

        interviewers.setCellFactory(CellFactoryFactory.getCellFactoryForInterviewer());
        pollInterviewers();

        selectedApplicants.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());

        selectedInterviewers.setCellFactory(CellFactoryFactory.getCellFactoryForInterviewer());

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

    private void addItemToOther(MouseEvent click, ListView source, ListView destination) {
        if (click.getClickCount() == 2) {
            User user = (User) source.getSelectionModel().getSelectedItem();
            destination.getItems().add(user);
            source.getItems().remove(user);
        }
    }

    private void pollApplicants() {
        for (Applicant applicant : interview.getApplicants()) {
            applicants.getItems().add(applicant);
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


        class UISelectionStrategy implements InterviewGroupAssignmentStrategy {

            @Override
            public List<InterviewGroup> select(List<Applicant> _applicants, List<Interviewer> _interviewers) {
                InterviewGroup group = new InterviewGroup(interview.getJob(),
                        selectedInterviewers.getItems().get(0),
                        new ArrayList<>(selectedApplicants.getItems()));
                List<InterviewGroup> groups = new ArrayList<>();
                groups.add(group);
                return groups;
            }
        }
        company = getSystem().getCompany(((HR) getUser()).getCompany());
        //start the first round of interview
        interview.toNextRound();
        interview.assignRound(new UISelectionStrategy(),
                new ArrayList<>(company.getInterviewerManager().getUsers().values()));
        // for those unselected, they are not qualified to the next round
        showModal("Great", "Assign successfully");
        listOfInterviews.remove(interview);
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}