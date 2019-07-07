package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.util.Callback;
import project.application.Application;
import project.application.Company;
import project.application.JobPosting;
import project.application.JobPostingManager;
import project.interview.Interview;
import project.interview.InterviewGroup;
import project.user.Applicant;
import project.user.User;
import project.user.Interviewer;
import project.user.InterviewerManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

class Group{
    Interviewer interviewer;
    LinkedHashSet<Applicant> applicants;
    Group(){
        applicants = new LinkedHashSet<>();
    }
    void add(Applicant applicant){
        applicants.add(applicant);
    }
    void add(Interviewer interviewer){
        this.interviewer = interviewer;
    }
    void remove(Applicant applicant){
        applicants.remove(applicant);
    }
    List<Applicant> toList(){
        return new ArrayList<>(applicants);
    }
}
public class AssignInterviewsController extends ApplicationController {

    @FXML
    ListView<Applicant> applicants;
    @FXML
    ListView<Interviewer> interviewers;
    @FXML
    ListView<User> interviewGroup;

    private Group group = new Group();
    static JobPosting jobPosting = null;


    @Override
    void postInit(){
        applicants.setCellFactory(new Callback<ListView<Applicant>, ListCell<Applicant>>() {

            @Override
            public ListCell<Applicant> call(ListView<Applicant> p) {

                ListCell<Applicant> cell = new ListCell<Applicant>() {

                    @Override
                    protected void updateItem(Applicant t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRealName());
                        }
                    }
                };
                return cell;
            }
        });
        pollApplicants();

        interviewers.setCellFactory(new Callback<ListView<Interviewer>, ListCell<Interviewer>>() {

            @Override
            public ListCell<Interviewer> call(ListView<Interviewer> p) {

                ListCell<Interviewer> cell = new ListCell<Interviewer>() {

                    @Override
                    protected void updateItem(Interviewer t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRealName());
                        }
                    }
                };
                return cell;
            }
        });
        pollInterviewers();

        interviewGroup.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {

            @Override
            public ListCell<User> call(ListView<User> p) {

                ListCell<User> cell = new ListCell<User>() {

                    @Override
                    protected void updateItem(User t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getRealName() + (t.getType() == User.Type.INTERVIEWER ? "(Interviewer)"
                                    : "(Applicant)"));
                        }else{
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });

        applicants.setOnDragDetected((MouseEvent event) -> {
            //We want the textArea to be dragged. Could also be copied.
            Dragboard db = applicants.startDragAndDrop(TransferMode.MOVE);

            // Put a string on a dragboard as an identifier
            ClipboardContent content = new ClipboardContent();
            content.putString(applicants.getId());
            db.setContent(content);

            //Consume the event
            event.consume();
        });

        interviewers.setOnDragDetected((MouseEvent event) -> {
            //We want the textArea to be dragged. Could also be copied.
            Dragboard db = interviewers.startDragAndDrop(TransferMode.MOVE);

            // Put a string on a dragboard as an identifier
            ClipboardContent content = new ClipboardContent();
            content.putString(interviewers.getId());
            db.setContent(content);

            //Consume the event
            event.consume();
        });

        interviewGroup.addEventHandler(DragEvent.DRAG_OVER, (DragEvent event) -> {
            if (event.getGestureSource() != interviewGroup
                    && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        interviewGroup.addEventHandler(DragEvent.DRAG_DROPPED, (DragEvent event) -> {
            //Get the dragboard back
            Dragboard db = event.getDragboard();
            boolean success = false;
            //Could have some more thorough checks of course.
            if (db.hasString()) {
                //Get the textarea and place it into flowPane2 instead
                group.add(applicants.getSelectionModel().getSelectedItem());
                group.add(interviewers.getSelectionModel().getSelectedItem());
                success = true;
                interviewGroup.getItems().clear();
                if(group.interviewer != null)
                    interviewGroup.getItems().add(group.interviewer);
                for(Applicant applicant : group.toList()){
                    interviewGroup.getItems().add(applicant);
                }
            }
            //Complete and consume the event.
            event.setDropCompleted(success);
            event.consume();
        });
    }



    private void pollApplicants(){
        if (jobPosting != null){
            for(Application application : jobPosting.getApplications()){
                applicants.getItems().add(application.getApplicant());
            }
        }
    }

    public void addUser(User user){
        interviewGroup.addEventHandler(DragEvent.DRAG_DROPPED, (DragEvent event) -> {
            //Get the dragboard back
            Dragboard db = event.getDragboard();
            boolean success = false;
            //Could have some more thorough checks of course.
            if (db.hasString()) {
                //Get the textarea and place it into flowPane2 instead
                interviewGroup.getItems().add(user);
                success = true;
            }
            //Complete and consume the event.
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void pollInterviewers(){
        if (getUser() != null){
            String companyName = getUser().getCompany();
            Company company = getSystem().getCompany(companyName);
            InterviewerManager interviewerManager = company.getInterviewerManager();
            for(Interviewer interviewer : interviewerManager.getUsers().values()){
                interviewers.getItems().add(interviewer);
            }
        }
    }

    public void submitButton(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    public void returnButton(ActionEvent event) throws IOException{
        SceneSwitcher.switchScene(this, event, "PostingApplicants.fxml");
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
