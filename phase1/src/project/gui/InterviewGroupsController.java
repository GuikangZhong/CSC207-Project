package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.application.*;
import project.interview.Interview;
import project.interview.InterviewGroup;
import project.user.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class InterviewGroupsController extends ApplicationController implements Initializable {
    @FXML
    private ListView<InterviewGroup> interviewGroups;

    @FXML
    private ListView<CheckBox> applicantCheckBoxs;


    private List<Applicant> applicantList;

    @Override
    void postInit() {
        super.postInit();
        interviewGroups.setCellFactory(CellFactoryFactory.getCellFactoryForInterviewGroup());

        pollInterviewGroups();
    }

    private void pollInterviewGroups() {
        Interviewer interviewer = (Interviewer) getUser();
        Company company = getSystem().getCompany(interviewer.getCompany());
        JobPostingManager manager = company.getJobPostingManager();
        //InterviewerManager manager = company.getInterviewerManager();
        interviewGroups.getItems().clear();
        for (JobPosting jobPosting : manager.getJobPostings().values()) {
            for (InterviewGroup interviewGroup : interviewer.getInterviews())
                if (interviewGroup.getInterview().getJobPosting() == jobPosting) {
                    interviewGroups.getItems().add(interviewGroup);
                }
        }

    }

    private void pollApplicants() {
        InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
        applicantCheckBoxs.getItems().clear();
        if (interviewGroup != null) {
            for (Applicant applicant : interviewGroup.getApplicants()) {
                applicantCheckBoxs.getItems().add(new CheckBox(applicant.getRealName()));
            }
            applicantList = interviewGroup.getApplicants();
        } else {
            throw new RuntimeException("Null InterviewGroup");
        }
    }

    public void interviewGroupClicked(MouseEvent event) {
        pollApplicants();

    }

    public void interviewClicked(MouseEvent event) {
        pollApplicants();
    }

    public void promoteButton(ActionEvent event) throws IOException {
        InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
        HashMap<String, String> map = new HashMap<>();
        for (Applicant applicant : applicantList) {
            map.put(applicant.getRealName(), applicant.getUsername());
        }
        for (CheckBox box : applicantCheckBoxs.getItems()) {
            if (box.isSelected()) {
                interviewGroup.setApplicantPassed(map.get(box.getText()), true);
            }
        }
        interviewGroup.submit();
        SceneSwitcher.switchScene(this, event, "InterviewerMenu.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

