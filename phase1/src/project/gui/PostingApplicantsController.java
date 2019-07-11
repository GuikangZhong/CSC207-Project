package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import project.interview.InterviewGroup;
import project.user.Applicant;
import project.user.HR;

import java.io.IOException;
import java.util.List;

public class PostingApplicantsController extends ApplicationController {
    @FXML
    private ListView<JobPosting> jobPostings;

    @FXML
    private ListView<Application> applications;



    @Override
    void postInit() {
        super.postInit();
        jobPostings.setCellFactory(new Callback<ListView<JobPosting>, ListCell<JobPosting>>() {

            @Override
            public ListCell<JobPosting> call(ListView<JobPosting> p) {

                ListCell<JobPosting> cell = new ListCell<JobPosting>() {

                    @Override
                    protected void updateItem(JobPosting t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getJobTitle() + " (" + t.getStatus()+")");
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
                        } else {
                            setText("");
                        }
                    }

                };
                return cell;
            }
        });
        pollJobPostings();
    }

    private void pollJobPostings() {
        HR hr = (HR) getUser();
        Company company = getSystem().getCompany(hr.getCompany());
        JobPostingManager manager = company.getJobPostingManager();
        jobPostings.getItems().clear();
        for (JobPosting jobPosting : manager.getJobPostings().values()) {
            jobPostings.getItems().add(jobPosting);
        }

    }

    private void pollApplicants() {
        JobPosting jobPosting = jobPostings.getSelectionModel().getSelectedItem();
        applications.getItems().clear();
        if (jobPosting != null) {
            for (Application application : jobPosting.getApplications()) {
                applications.getItems().add(application);
            }
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    public void applicationClicked(MouseEvent event) {
//        final Applicant applicant = applicantList.get(index);
//        final InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            showModal(stage -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicationInfo.fxml"));
                    AnchorPane pane = (AnchorPane) loader.load();
                    ApplicationInfoController controller = loader.<ApplicationInfoController>getController();
//                Application application = applicant.getApplicationOf(interviewGroup.getJob().getTitle()).get();
                    Application application = applications.getSelectionModel().getSelectedItem();
                    controller.setApplication(application);
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void jobPostingClicked(MouseEvent event) {
        applications.getItems().clear();
        pollApplicants();
        JobPosting jobPosting = jobPostings.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && ((jobPosting.getStatus() == JobPosting.Status.FILLED) ||
                (jobPosting.getStatus() == JobPosting.Status.UNFILLED))){
            StringBuilder builder = new StringBuilder();
            for (Applicant hiredApplicant: jobPosting.getHireResult().getHired()){
                builder.append(hiredApplicant.getRealName()).append("\n");
            }
            showModal("Hired Applicant(s)", builder.toString());
        }
    }

}
