package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.application.Application;
import project.application.Company;
import project.application.JobPosting;
import project.application.JobPostingManager;
import project.user.HR;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class RefereeMenu extends ApplicationController implements Serializable {
    private static final long serialVersionUID = -5054910626456091917L;

    @FXML
    ListView<JobPosting> jobPostings;
    @FXML
    ListView<Application> applications;

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

    public void uploadButton(ActionEvent event) throws IOException{
        //TODO: upload a letter for a applicant
    }

    private void pollJobPostings() {
        List<JobPosting> allPostings = getSystem().getAllJobPostings();
        jobPostings.getItems().clear();
        for (JobPosting jobPosting : allPostings) {
            jobPostings.getItems().add(jobPosting);
        }
    }

    public void applicationClicked(MouseEvent event) {
        JobPostingsApplicantsLoader loader = new JobPostingsApplicantsLoader();
        loader.applicationClicked(event, applications);
    }

    public void jobPostingClicked(MouseEvent event) {
        JobPostingsApplicantsLoader loader = new JobPostingsApplicantsLoader();
        loader.jobPostingClicked(event, jobPostings, applications);
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
