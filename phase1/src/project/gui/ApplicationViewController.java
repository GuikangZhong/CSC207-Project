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
import project.user.Applicant;

import java.io.IOException;
import java.util.List;

public class ApplicationViewController extends ApplicationController {
    @FXML
    private ListView<Application> applications;

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

    void pollApplications() {
        applications.getItems().clear();
        Applicant applicant = (Applicant) getUser();
        for (Application application : applicant.getApplications()) {
            applications.getItems().add(application);
        }
        applications.getSelectionModel().clearSelection();
        applications.refresh();
    }

    @Override
    void postInit() {
        super.postInit();

        applications.setCellFactory(new Callback<ListView<Application>, ListCell<Application>>() {

            @Override
            public ListCell<Application> call(ListView<Application> p) {

                ListCell<Application> cell = new ListCell<Application>() {

                    @Override
                    protected void updateItem(Application t, boolean bln) {
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
        pollApplications();


    }

    public void applicationClicked(MouseEvent event){
        Application application = applications.getSelectionModel().getSelectedItem();
        showModal("Status", application.getStatus().toString());
    }

    public void withdraw(ActionEvent event) {
        Application application = applications.getSelectionModel().getSelectedItem();
        Applicant applicant = (Applicant) getUser();
        Company company = application.getJob().getCompany();
        JobPosting jobPosting = company.getJobPostingManager().getJobPosting(application.getJob().getTitle());
        applicant.withdraw(jobPosting, application);
        pollApplications();
        showModal("Great", "withdrawn");
    }
}
