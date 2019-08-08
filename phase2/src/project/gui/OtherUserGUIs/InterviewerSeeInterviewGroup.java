package project.gui.OtherUserGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import project.application.*;
import project.gui.ApplicantGUIs.ApplicationInfoController;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.CellFactoryFactory;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.interview.InterviewGroup;
import project.user.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class InterviewerSeeInterviewGroup extends ApplicationController implements Initializable {
    @FXML
    private ListView<InterviewGroup> interviewGroups;

    @FXML
    private ListView<CheckBox> applicantCheckBoxes;


    private List<Applicant> applicantList;

    @Override
    public void postInit() {
        super.postInit();
        interviewGroups.setCellFactory(CellFactoryFactory.getCellFactoryForInterviewGroup());

        pollInterviewGroups();
    }

    private void pollInterviewGroups() {
        Interviewer interviewer = (Interviewer) getUser();
        interviewGroups.getItems().clear();
        for (InterviewGroup interviewGroup : interviewer.getInterviews()) {
            interviewGroups.getItems().add(interviewGroup);
        }
    }

    private void pollApplicants() {
        InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
        applicantCheckBoxes.getItems().clear();
        if (interviewGroup != null) {
            for (Applicant applicant : interviewGroup.getApplicants()) {
                applicantCheckBoxes.getItems().add(new CheckBox(applicant.getRealName()));
            }
            applicantList = interviewGroup.getApplicants();
        }
    }

    public void interviewGroupClicked(MouseEvent event) {
        pollApplicants();

    }

    public void promoteButton(ActionEvent event) throws IOException {
        InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
        HashMap<String, String> map = new HashMap<>();
        for (Applicant applicant : applicantList) {
            map.put(applicant.getRealName(), applicant.getUsername());
        }
        for (CheckBox box : applicantCheckBoxes.getItems()) {
            if (box.isSelected()) {
                interviewGroup.setApplicantPassed(map.get(box.getText()), true);
            }
        }
        interviewGroup.submit();
        SceneSwitcher.switchScene(this, event, "InterviewerSeeInterviewGroups.fxml");
    }

    public void viewApplicant(ActionEvent event){
        int index = applicantCheckBoxes.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            final Applicant applicant = applicantList.get(index);
            final InterviewGroup interviewGroup = interviewGroups.getSelectionModel().getSelectedItem();
            showModal(stage -> {
                try {
                    FXMLLoader loader =
                            new FXMLLoader(getClass().getResource("../ApplicantGUIs/ApplicationInfo.fxml"));
                    AnchorPane pane = loader.load();
                    ApplicationInfoController controller = loader.<ApplicationInfoController>getController();
                    Application application
                            = applicant.getApplicationOf(interviewGroup.getJobPosting().getJobTitle()).get();
                    controller.setApplication(application);
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                }catch(IOException e){
                    e.printStackTrace();
                }
            });
        }

    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
    }
}

