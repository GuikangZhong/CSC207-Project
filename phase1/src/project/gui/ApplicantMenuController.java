package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import project.application.CoverLetter;
import project.application.Document;
import project.user.Applicant;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicantMenuController implements Initializable {
    @FXML
    private TreeView<String> options;

    static private TreeItem<String> option = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(option == null) {
            option = new TreeItem<>("Option");
            TreeItem<String> dashboard = new TreeItem<>("Dashboard");
            TreeItem<String> document = new TreeItem<>("Document");
            TreeItem<String> jobPosting = new TreeItem<>("Job posting");
            TreeItem<String> application = new TreeItem<>("Application");
            TreeItem<String> history = new TreeItem<>("Your history");


            option.getChildren().addAll(dashboard, document, jobPosting, application, history);

            TreeItem<String> coverLetter = new TreeItem<>("Cover letter");
            TreeItem<String> cv = new TreeItem<>("CV");
            document.getChildren().addAll(coverLetter, cv);

            option.setExpanded(true);


            System.out.println("init");
        }
        options.setRoot(option);
    }

    public void selectItems(MouseEvent event) throws IOException{
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item != null){
            if (item.getValue().equals("Cover letter")){
                SceneSwitcher.switchScene(this.getClass(), event, "Document.fxml");
            }
            else if (item.getValue().equals("CV")){
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

    public void uploadButton(ActionEvent event) throws IOException{
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            CoverLetter coverLetter = CoverLetter.createByFileName(selectedFile.getName(), selectedFile.getAbsolutePath(), Main.system.now());
            ((Applicant)Main.user).addDocument(coverLetter);
            System.out.println(selectedFile.getName());
        }

    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}