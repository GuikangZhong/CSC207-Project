package project.gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicantMenuController implements Initializable {
    @FXML
    private TreeView<String> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> option = new TreeItem<>("Option");
        TreeItem<String> dashboard = new TreeItem<>("Dashboard");
        TreeItem<String> document = new TreeItem<>("Document");
        TreeItem<String> jobPosting = new TreeItem<>("Job posting");
        TreeItem<String> application = new TreeItem<>("Application");
        TreeItem<String> history = new TreeItem<>("Your history");


        option.getChildren().addAll(dashboard, document, jobPosting, application, history);
        option.setExpanded(true);


        options.setRoot(option);
    }

    public void selectItems(MouseEvent event) throws IOException{
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item != null){
            if (item.getValue().equals("Document")){
                SceneSwitcher.switchScene(this.getClass(), event, "Document.fxml");
            }
            else if (item.getValue().equals("Job posting")){
                SceneSwitcher.switchScene(this.getClass(), event, "JobPosting.fxml");
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

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
