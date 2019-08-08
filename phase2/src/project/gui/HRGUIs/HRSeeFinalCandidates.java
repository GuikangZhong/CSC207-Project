package project.gui.HRGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.CellFactoryFactory;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.interview.Interview;
import project.user.Applicant;
import project.user.HR;
import project.user.User;

import java.io.IOException;
import java.util.Map;

public class HRSeeFinalCandidates extends ApplicationController {
    @FXML
    private ListView<Interview> interviewList = new ListView<>();
    @FXML
    private ListView<Applicant> applicantList = new ListView<>();
    @FXML
    private ListView<Applicant> selectedApplicants = new ListView<>();

    @Override
    public void postInit() {
        super.postInit();
        Callback<ListView<Interview>, ListCell<Interview>> factory =
                new Callback<ListView<Interview>, ListCell<Interview>>() {

            @Override
            public ListCell<Interview> call(ListView<Interview> p) {

                ListCell<Interview> cell = new ListCell<Interview>() {

                    @Override
                    protected void updateItem(Interview t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            String s = t.getJobPosting().getJobTitle();
                            setText(s);
                        } else {
                            setText("");
                        }
                    }

                };

                return cell;
            }
        };
        interviewList.setCellFactory(factory);
        applicantList.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());
        selectedApplicants.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());
        pollInterviews();
    }

    private void pollInterviews() {
        Map<String, Interview> interviewMap = ((HR) user).getFinalCandidates();
        if (interviewMap != null) {
            for (Interview interview : interviewMap.values()) {
                interviewList.getItems().add(interview);
            }
        }
    }

    public void pollApplicants() {
        Interview interview = interviewList.getSelectionModel().getSelectedItem();
        System.out.println(interview);
        applicantList.getItems().clear();
        if (interview != null) {
            for (Applicant applicant : interview.getApplicants()) {
                applicantList.getItems().add(applicant);
            }
        }
    }


    public void clickOnApplicantList(MouseEvent event) {
        addItemToOther(event, applicantList, selectedApplicants);
    }

    public void clickOnSelectedApplicants(MouseEvent event) {
        addItemToOther(event, selectedApplicants, applicantList);
    }

    private void addItemToOther(MouseEvent click, ListView source, ListView destination) {
        if (click.getClickCount() == 2) {
            User user = (User) source.getSelectionModel().getSelectedItem();
            destination.getItems().add(user);
            source.getItems().remove(user);
        }
    }

    public void hireButton(ActionEvent event) {
        Interview interview = interviewList.getSelectionModel().getSelectedItem();
        if (selectedApplicants.getItems().size() <= interview.getNumberNeeded()) {
            interview.hireFromFinalCandidates(selectedApplicants.getItems());
            for (Applicant applicant: applicantList.getItems()){
                if (selectedApplicants.getItems().contains(applicant)){
                    applicant.addHired(interview.getJobPosting());
                }
                else{
                    applicant.addRejected(interview.getJobPosting());
                }
                applicant.moveToApplied(interview.getJobPosting());
            }
            interviewList.getItems().remove(interview);
            applicantList.getItems().clear();
            selectedApplicants.getItems().clear();
            showModal("Great", "Hired successfully");
        } else {
            showModal("Bad", "You have selected too many applicants");
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

}
