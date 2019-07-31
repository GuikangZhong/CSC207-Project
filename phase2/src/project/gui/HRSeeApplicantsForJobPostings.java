package project.gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import project.application.Application;
import project.application.Company;
import project.application.JobPosting;
import project.application.JobPostingManager;
import project.user.Applicant;
import project.user.HR;

import java.io.IOException;

public class HRSeeApplicantsForJobPostings extends ApplicationController {
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
                            setText(t.getJobTitle() + " (" + t.getStatus() + ")");
                        } else {
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
        Company company = getSystem().getCompany(hr.getSignedInCompany());
        JobPostingManager manager = company.getJobPostingManager();
        jobPostings.getItems().clear();
        for (JobPosting jobPosting : manager.getJobPostings().values()) {
            jobPostings.getItems().add(jobPosting);
        }

    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    public void applicationClicked(MouseEvent event) {
        JobPostingsApplicantsLoader loader = new JobPostingsApplicantsLoader();
        loader.applicationClicked(event, applications);
    }

    public void jobPostingClicked(MouseEvent event) {
        JobPostingsApplicantsLoader loader = new JobPostingsApplicantsLoader();
        loader.jobPostingClicked(event, jobPostings, applications);
    }

}
