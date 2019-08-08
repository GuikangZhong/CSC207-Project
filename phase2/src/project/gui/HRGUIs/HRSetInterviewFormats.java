package project.gui.HRGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import project.application.Company;
import project.application.JobPosting;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.interview.Interview;
import project.interview.InterviewSetup;
import project.interview.Round;
import project.user.HR;

import java.io.IOException;


public class HRSetInterviewFormats extends ApplicationController {
    @FXML
    private ListView<JobPosting> jobPostings;

    @FXML
    private ListView<String> formats;

    @FXML
    private TextArea overview;

    private HR hr;

    private Company company;

    @FXML
    private Label companyName;

    @Override
    public void postInit() {
        super.postInit();
        hr = (HR) getUser();
        company = hr.getSignedInCompany();
        companyName.setText(company.getName());
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
        jobPostings.getItems().addAll(hr.getPostingsToAssignFormat());
        initializeFormats();
    }

    private void initializeFormats(){
        for (String format: company.getFormatNames()){
            formats.getItems().add(format);
        }
        formats.setOnMouseClicked(e -> displayOverview());
    }

    private void displayOverview(){
        overview.clear();
        String formatName = formats.getSelectionModel().getSelectedItem();
        InterviewSetup interviewFormat = company.getInterviewFormat(formatName);
        StringBuilder info = new StringBuilder();
        for (Round round: interviewFormat.getRounds()){
            info.append(round.toString()).append("\n");
        }
        overview.setText(info.toString());
    }

    public void submitClicked(ActionEvent event){
        JobPosting selectedPosting = jobPostings.getSelectionModel().getSelectedItem();
        int indexSelected = jobPostings.getSelectionModel().getSelectedIndex();
        String formatName = formats.getSelectionModel().getSelectedItem();
        InterviewSetup tempFormat = company.getInterviewFormat(formatName);
        InterviewSetup setupAssigned = tempFormat.createSetupWithJob(selectedPosting);
        Interview interview = new Interview(hr, selectedPosting, setupAssigned);
        interview.addObserver(company.getHrManager());
        hr.addInterviewsToBeScheduled(interview);
        hr.postingAssignedFormat(selectedPosting);
        jobPostings.getItems().remove(indexSelected);
    }

    public void createNewFormat(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this, event, "HRCreateInterviewFormat.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
