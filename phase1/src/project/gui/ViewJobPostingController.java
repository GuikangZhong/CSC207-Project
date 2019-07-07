package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import project.application.JobPosting;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewJobPostingController extends ApplicationController implements Initializable{
    @FXML
    private ListView<JobPosting> jobList;
    @FXML
    private TextArea description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       super.initialize(location,resources);


    }
    @Override
    void postInit(){
        super.postInit();
        // fill the document list
        List<JobPosting> jobPostings = getSystem().getAllJobPostings();
        if (jobPostings.size() != 0) {
            for (JobPosting jp : jobPostings){
                jobList.getItems().add(jp);
            }
        }
    }

    public void selectJobPosting(MouseEvent event) throws IOException {
        JobPosting jobPosting = jobList.getSelectionModel().getSelectedItem();
        if (jobPosting != null) {
            description.setText(jobPosting.getDescription());
        }
    }

    public void applyButton(ActionEvent event){

    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}

