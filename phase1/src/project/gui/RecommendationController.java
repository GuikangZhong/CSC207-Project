package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.application.Application;
import project.application.JobPosting;
import project.interview.Interview;
import project.user.Applicant;
import project.user.HR;
import project.user.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendationController extends ApplicationController {
    @FXML
    private ListView<Interview> interviewList = new ListView<>();
    @FXML
    private ListView<Applicant> applicantList = new ListView<>();
    @FXML
    private ListView<Applicant> selectedApplicants = new ListView<>();

    @Override
    void postInit() {
        super.postInit();
        Callback<ListView<Interview>, ListCell<Interview>> factory = new Callback<ListView<Interview>, ListCell<Interview>>() {

            @Override
            public ListCell<Interview> call(ListView<Interview> p) {

                ListCell<Interview> cell = new ListCell<Interview>() {

                    @Override
                    protected void updateItem(Interview t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            String s = t.getJob().getTitle();
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

    private void pollInterviews(){
        Map<String, Interview> interviewMap = ((HR)user).getRecommendationLists();
        if (interviewMap != null){
            for (Interview interview: interviewMap.values()){
                interviewList.getItems().add(interview);
            }
        }
    }

    public void pollApplicants(){
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

    public void hireButton(ActionEvent event){
        interviewList.getSelectionModel().getSelectedItem().hireFromRecommendation(selectedApplicants.getItems());
        showModal("Hired successfully");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }

}
