package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import project.application.Application;
import project.application.Company;
import project.application.JobPosting;
import project.application.JobPostingManager;
import project.interview.Interview;
import project.user.Applicant;
import project.user.HR;

import java.io.IOException;
import java.util.List;

public class InterviewAssignmentController extends ApplicationController {
    @FXML
    private ListView<Interview> interviews;

    @FXML
    private ListView<Application> applications;
    @FXML
    private Button assignment;


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
                            setText(t.getJob().getTitle());
                        }else{
                            setText("");
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

    private boolean isJobPostingDue(JobPosting jobPosting){
        if (jobPosting.getStatus() == JobPosting.Status.CLOSED){
            return true;
        }
        return false;
    }

    private void pollJobPostings(){
        HR hr = (HR)getUser();
        Company company = getSystem().getCompany(hr.getCompany());
        JobPostingManager manager = company.getJobPostingManager();
        // get the job postings managed by the HR
        List<JobPosting> jobPostingList = manager.getJobPostingsForHR(hr.getUsername());
        jobPostings.getItems().clear();
        for(JobPosting jobPosting: jobPostingList){
            jobPostings.getItems().add(jobPosting);
        }

    }

    private void pollApplicants(){
        JobPosting jobPosting = jobPostings.getSelectionModel().getSelectedItem();
        applications.getItems().clear();
        if (jobPosting != null) {
            for(Application application : jobPosting.getApplications()){
                applications.getItems().add(application);
            }
        }
    }
    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    public void applicationClicked(MouseEvent event){

    }

    public void jobPostingClicked(MouseEvent event){
        pollApplicants();
    }

    public void assignmentButton(ActionEvent event) throws IOException{
        Interview jobPosting = this.interviews.getSelectionModel().getSelectedItem();

        HR hr = (HR) getUser();
        Company company = getSystem().getCompany(hr.getCompany());
        Interview interview = company.getInterviewManager().getInterview(jobPosting.getJobTitle());
        SceneSwitcher.<AssignInterviewsController, InterviewAssignmentController>switchScene(this, event, "AssignInterviews.fxml", (next, current) -> {
            if (interview.getRound() == -1){
                next.company = company;
                next.listOfInterviews = hr.getInterviewsToBeScheduled();
                next.interview = interview;
            } else {
                next.company = company;
                next.listOfInterviews = hr.getInterviewsRoundFinished();
                next.interview = interview;
            }
        });


    }

}
