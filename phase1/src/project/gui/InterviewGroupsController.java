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

public class InterviewGroupsController extends ApplicationController implements Initializable {
    @FXML
    private ListView<InterviewGroup> interviewGroups;

    @FXML
    private ListView<Application> applications;

    @Override
    void postInit(){
        super.postInit();
        interviewGroups.setCellFactory(new Callback<ListView<InterviewGroup>, ListCell<InterviewGroup>>() {

            @Override
            public ListCell<InterviewGroup> call(ListView<InterviewGroup> p) {

                ListCell<InterviewGroup> cell = new ListCell<InterviewGroup>() {

                    @Override
                    protected void updateItem(InterviewGroup t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getInterview().getJobPosting().getJobTitle());
                        }
                    }

                };

                return cell;
            }
        });
        applications.setCellFactory(new Callback<ListView<Application>, ListCell<Application>>() {

            @Override
            public ListCell<Application> call(ListView<Application> p) {

                ListCell<Application> cell = new ListCell<Application>() {

                    @Override
                    protected void updateItem(Application t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getApplicant().getRealName());
                        }
                    }

                };
                return cell;
            }
        });
        pollInterviewGroups();
    }

    private void pollInterviewGroups(){
        Interviewer interviewer = (Interviewer) getUser();
        Company company = getSystem().getCompany(interviewer.getCompany());
        JobPostingManager manager = company.getJobPostingManager();
        //InterviewerManager manager = company.getInterviewerManager();
        interviewGroups.getItems().clear();
        for(JobPosting jobPosting: manager.getJobPostings().values()){
            for(InterviewGroup interviewGroup : interviewer.getInterviews())
                if(interviewGroup.getInterview().getJobPosting() == jobPosting) {
                    interviewGroups.getItems().add(interviewGroup);
                }
        }

    }

    private void pollApplicants(){
        InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
        applications.getItems().clear();
        if (interviewGroup != null) {
            for(Application application : interviewGroup.getInterview().getJobPosting().getApplications()){
                applications.getItems().add(application);
            }
        }
    }

    public void interviewGroupClicked(MouseEvent event){
        pollInterviewGroups();

    }

    public void interviewClicked(MouseEvent event){
        pollApplicants();
    }

    public void promoteButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "InterviewerMenu.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

