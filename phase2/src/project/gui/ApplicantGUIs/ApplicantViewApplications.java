package project.gui.ApplicantGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import project.application.Application;
import project.application.Company;
import project.application.JobPosting;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.CellFactoryFactory;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.user.Applicant;

import java.io.IOException;

public class ApplicantViewApplications extends ApplicationController {
    @FXML
    private ListView<Application> applications;

    @FXML
    private TextArea applicationStatus;

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
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
    public void postInit() {
        super.postInit();

        applications.setCellFactory(new Callback<ListView<Application>, ListCell<Application>>() {

            @Override
            public ListCell<Application> call(ListView<Application> p) {

                return new ListCell<Application>() {

                    @Override
                    protected void updateItem(Application t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getJobPosting().getJobTitle());
                        } else {
                            setText("");
                        }
                    }

                };
            }
        });
        pollApplications();


    }

    public void applicationClicked(MouseEvent event) {
        Application application = applications.getSelectionModel().getSelectedItem();
        if (application == null) return;
        if (event.getClickCount() == 2) {
            showModal(stage -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicationInfo.fxml"));
                    AnchorPane pane = (AnchorPane) loader.load();
                    ApplicationInfoController controller = loader.getController();
                    controller.setApplication(application);
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            applicationStatus.setText(application.getRoundsFinishedInfo());
        }
    }

    public void withdraw(ActionEvent event) {
        Application application = applications.getSelectionModel().getSelectedItem();
        Applicant applicant = (Applicant) getUser();
        Company company = application.getJobPosting().getCompany();
        JobPosting jobPosting = company.getJobPostingManager().getJobPosting(application.getJobPosting().getJobTitle());
        boolean withdraw = applicant.withdraw(jobPosting, application);
        pollApplications();
        if (withdraw)
            showModal("Great", "withdrawn");
        else
            showModal("Bad", "Unable to withdraw");
    }
}
