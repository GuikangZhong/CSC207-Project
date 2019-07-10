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
import java.util.*;
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

    @FXML
    private ListView<Group> interviewGroups = new ListView<>();

    private Group previousSelectedGroup;

    class Group {
        Interviewer interviewer;
        List<Applicant> applicants;
        int id;

        Group(int id) {
            this.id = id;
            applicants = new ArrayList<>();
        }
    }

    List<Interview> listOfInterviews;
    Interview interview;
    Company company;
    private int groupCount = 1;


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
            if(selectedInterviewers.getItems().isEmpty())
                addItemToOther(click, interviewers, selectedInterviewers);
        });

        selectedInterviewers.setOnMouseClicked((MouseEvent click) -> {
            addItemToOther(click, selectedInterviewers, interviewers);
        });

        interviewGroups.setCellFactory(new Callback<ListView<Group>, ListCell<Group>>() {
            @Override
            public ListCell<Group> call(ListView<Group> p) {

                ListCell<Group> cell = new ListCell<Group>() {

                    @Override
                    protected void updateItem(Group t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText("Group#" + t.id);
                        } else {
                            setText("");
                        }

                    }
                };
                return cell;
            }
        });
        interviewGroups.getItems().add(new Group(groupCount++));
        interviewGroups.getSelectionModel().select(0);
        previousSelectedGroup = interviewGroups.getSelectionModel().getSelectedItem();
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
        saveGroup();
        class SelectionException extends RuntimeException {

            private static final long serialVersionUID = 6682675575061145281L;

            public SelectionException(String s) {
                super(s);
            }
        }
        class UISelectionStrategy implements InterviewGroupAssignmentStrategy {

            @Override
            public List<InterviewGroup> select(List<Applicant> _applicants, List<Interviewer> _interviewers) {
                List<InterviewGroup> result = new ArrayList<>();
                Set<String> set = new HashSet<>();
                for (Applicant applicant : _applicants) {
                    set.add(applicant.getUsername());
                }
                for (Group group : interviewGroups.getItems()) {
                    if (group.interviewer == null) {
                        throw new SelectionException("You must select an interviewer for group#" + group.id);
                    }
                    for (Applicant applicant : group.applicants) {
                        set.remove(applicant.getUsername());
                    }
                    InterviewGroup interviewGroup = new InterviewGroup(interview.getJob(),
                            group.interviewer,
                            group.applicants);
                    result.add(interviewGroup);
                }
                if (!set.isEmpty()) {
                    throw new SelectionException("You must select all applicants (remaining " + set.size() + ")");
                }
                return result;
            }
        }
        try {
            company = getSystem().getCompany((getUser()).getCompany());
            interview.assignRound(new UISelectionStrategy(),
                    new ArrayList<>(company.getInterviewerManager().getUsers().values()));
            showModal("Great", "Assign successfully");
            listOfInterviews.remove(interview);
            SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
        } catch (SelectionException e) {
            showModal("Bad", e.getMessage());
        }

    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    private void saveGroup(){
        if (previousSelectedGroup != null) {
            previousSelectedGroup.applicants = new ArrayList<>();
            if (!selectedInterviewers.getItems().isEmpty()) {
                previousSelectedGroup.interviewer = selectedInterviewers.getItems().get(0);
            } else {
                previousSelectedGroup.interviewer = null;
            }
            previousSelectedGroup.applicants.addAll(selectedApplicants.getItems());
        }
    }
    public void clickOnGroup(MouseEvent event) {
        saveGroup();
        Group group = interviewGroups.getSelectionModel().getSelectedItem();
        selectedApplicants.getItems().clear();
        selectedInterviewers.getItems().clear();
        if (group.interviewer != null) {
            selectedInterviewers.getItems().add(group.interviewer);
        }
        selectedApplicants.getItems().addAll(group.applicants);
        previousSelectedGroup = group;
    }

    public void addGroup(ActionEvent event) {
        interviewGroups.getItems().add(new Group(groupCount++));
    }

    public void removeGroup(ActionEvent event) {
        Group group = interviewGroups.getSelectionModel().getSelectedItem();
        if (group != null) {
            interviewGroups.getItems().remove(group);
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}