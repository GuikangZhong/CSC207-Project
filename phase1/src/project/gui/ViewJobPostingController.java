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

public class ViewJobPostingController implements Initializable {
    @FXML
    private TreeView<String> options;
    @FXML
    private ListView<JobPosting> jobList;
    @FXML
    private TextArea description;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // menu
        TreeItem<String> option = new TreeItem<>("Option");
        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> document = new TreeItem<>("Document");
        TreeItem<String> jobPosting = new TreeItem<>("Job posting");
        TreeItem<String> application = new TreeItem<>("Application");
        TreeItem<String> history = new TreeItem<>("Your history");
        option.getChildren().addAll(dashboard, document, jobPosting, application, history);
        option.setExpanded(true);
        options.setRoot(option);

        // fill the document list
        List<JobPosting> jobPostings = Main.system.getAllJobPostings();
        if (jobPostings.size() != 0) {
            for (JobPosting jp : jobPostings){
                jobList.getItems().add(jp);
            }
        }
    }

    public void selectItems(MouseEvent event) throws IOException{
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item != null){
            if (item.getValue().equals("Document")) {
                SceneSwitcher.switchScene(this.getClass(), event, "Document.fxml");
            }
            else if (item.getValue().equals("Job posting")){
                SceneSwitcher.switchScene(this.getClass(), event, "ViewJobPosting.fxml");
            }
            else if (item.getValue().equals("Application")){
                SceneSwitcher.switchScene(this.getClass(), event, "Application.fxml");
            }
            else if (item.getValue().equals("Your history")){
                SceneSwitcher.switchScene(this.getClass(), event, "YourHistory.fxml");
            }
            else if (item.getValue().equals("Dashboard")){
                SceneSwitcher.switchScene(this.getClass(), event, "ApplicantMenu.fxml");
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
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}

