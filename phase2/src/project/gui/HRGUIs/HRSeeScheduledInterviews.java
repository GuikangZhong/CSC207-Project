package project.gui.HRGUIs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import project.gui.GeneralUseGUIs.ApplicationController;
import project.gui.GeneralUseGUIs.SceneSwitcher;
import project.interview.Interview;
import project.user.HR;

import java.io.IOException;
import java.util.List;

public class HRSeeScheduledInterviews extends ApplicationController {
    @FXML
    private ListView<Interview> newInterviews;

    @FXML
    private ListView<Interview> interviewsRoundFinished;


    @Override
    public void postInit() {
        super.postInit();
        Callback<ListView<Interview>, ListCell<Interview>> factory = new Callback<ListView<Interview>, ListCell<Interview>>() {

            @Override
            public ListCell<Interview> call(ListView<Interview> p) {

                ListCell<Interview> cell = new ListCell<Interview>() {

                    @Override
                    protected void updateItem(Interview t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            String s = t.getJobPosting().getJobTitle();
                            s += "(" + t.getRoundInProgress() + ")";
                            setText(s);
                        } else {
                            setText("");
                        }
                    }

                };

                return cell;
            }
        };
        newInterviews.setCellFactory(factory);
        interviewsRoundFinished.setCellFactory(factory);
        pollJobPostings();
    }

    private void pollJobPostings() {
        HR hr = (HR) getUser();
        newInterviews.getItems().clear();
        for (Interview interview : hr.getInterviewsToBeScheduled()) {
            newInterviews.getItems().add(interview);
        }
        interviewsRoundFinished.getItems().clear();
        for (Interview interview : hr.getInterviewsRoundFinished()) {
            interviewsRoundFinished.getItems().add(interview);
        }

    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "../GeneralUseGUIs/Main.fxml");
    }

    public void newInterviewsClicked(MouseEvent event) {
        interviewsRoundFinished.getSelectionModel().clearSelection();
    }

    public void roundFinishedClicked(MouseEvent event) {
        newInterviews.getSelectionModel().clearSelection();
    }

    public void assignmentButton(ActionEvent event) throws IOException {
        HR hr = (HR) getUser();
        Interview selected = this.newInterviews.getSelectionModel().getSelectedItem();
        List<Interview> _interviews = hr.getInterviewsToBeScheduled();
        if (selected == null) {
            selected = this.interviewsRoundFinished.getSelectionModel().getSelectedItem();
            _interviews = hr.getInterviewsRoundFinished();
        }
        if (selected == null) {
            showModal("You have to select an interview first");
            return;
        }
        final Interview interview = selected;
        final List<Interview> interviews = _interviews;
        SceneSwitcher.<HRAssignInterviewGroups, HRSeeScheduledInterviews>switchScene(
                this, event, "HRAssignInterviewGroups.fxml", (next, current) -> {
                    next.listOfInterviews = interviews;
                    next.interview = interview;

                });


    }

}
