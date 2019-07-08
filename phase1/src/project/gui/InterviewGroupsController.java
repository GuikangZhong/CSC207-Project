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
    private ListView<Interview> interviews;

    @FXML
    private ListView<Application> applications;

    @Override
    void postInit(){
        super.postInit();
        interviews.setCellFactory(new Callback<ListView<Interview>, ListCell<Interview>>() {

            @Override
            public ListCell<Interview> call(ListView<Interview> p) {

                ListCell<Interview> cell = new ListCell<Interview>() {

                    @Override
                    protected void updateItem(Interview t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getJobPosting().getJobTitle());
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
        pollJobPostings();
    }

    private void pollJobPostings(){
        Interviewer interviewer = (Interviewer) getUser();
        Company company = getSystem().getCompany(interviewer.getCompany());
        JobPostingManager manager = company.getJobPostingManager();
        //InterviewerManager manager = company.getInterviewerManager();
        interviews.getItems().clear();
        for(JobPosting jobPosting: manager.getJobPostings().values()){
            for(InterviewGroup interviewGroup : interviewer.getInterviews())
                if(interviewGroup.getRound().getInterview().getJobPosting() == jobPosting) {
                    interviews.getItems().add(interviewGroup.getRound().getInterview());
                }
        }

    }

    private void pollApplicants(){
        Interview interview = interviews.getSelectionModel().getSelectedItem();
        applications.getItems().clear();
        if (interview != null) {
            for(Application application : interview.getJobPosting().getApplications()){
                applications.getItems().add(application);
            }
        }
    }

    public void applicationClicked(MouseEvent event){

    }

    public void jobPostingClicked(MouseEvent event){
        pollApplicants();
    }

    public void promoteButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "InterviewerMenu.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

