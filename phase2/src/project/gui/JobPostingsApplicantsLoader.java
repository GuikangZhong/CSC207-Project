package project.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.application.Application;
import project.application.JobPosting;
import project.user.Applicant;

import java.io.IOException;

import static project.gui.ApplicationController.showModal;

public class JobPostingsApplicantsLoader {

    void applicationClicked(MouseEvent event, ListView<Application> applications){
        if (event.getClickCount() == 2) {
            showModal(stage -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicationInfo.fxml"));
                    AnchorPane pane = (AnchorPane) loader.load();
                    ApplicationInfoController controller = loader.<ApplicationInfoController>getController();
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

    void jobPostingClicked(MouseEvent event, ListView<JobPosting> jobPostings, ListView<Application> applications) {
        applications.getItems().clear();
        pollApplicants(jobPostings,  applications);
        JobPosting jobPosting = jobPostings.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2 && ((jobPosting.getStatus() == JobPosting.Status.FILLED))){
            StringBuilder builder = new StringBuilder();
            for (Applicant hiredApplicant: jobPosting.getHiredApplicants()){
                builder.append(hiredApplicant.getRealName()).append(" (").
                        append(hiredApplicant.getUsername()).append(")").append("\n");
            }
            showModal("Hired Applicant(s)", builder.toString());
        }
    }

    private void pollApplicants(ListView<JobPosting> jobPostings, ListView<Application> applications) {
        JobPosting jobPosting = jobPostings.getSelectionModel().getSelectedItem();
        applications.getItems().clear();
        if (jobPosting != null) {
            for (Application application : jobPosting.getApplications()) {
                applications.getItems().add(application);
            }
        }
    }
}
