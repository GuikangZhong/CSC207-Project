package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import project.application.CV;
import project.application.CoverLetter;
import project.application.Document;
import project.user.Applicant;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoverLetterEditController implements Initializable {
    @FXML
    private TreeView<String> options;

    @FXML
    private TextArea editArea;

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
        TreeItem<String> coverLetter = new TreeItem<>("Cover letter");
        TreeItem<String> cv = new TreeItem<>("CV");
        document.getChildren().addAll(coverLetter, cv);
        option.setExpanded(true);
        options.setRoot(option);
    }

    private static String content(List<Document> documents){
        if (documents.size() == 0){
            return "No cover letter uploaded";
        }
        else{
            for (Document document: documents){
                if (document instanceof CoverLetter) {
                    return document.getContent();
                }
            }
            return "No cover letter uploaded";
        }
    }

    public void selectItems(MouseEvent event) throws IOException{
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item != null){
            if (item.getValue().equals("Cover letter")){
                SceneSwitcher.switchScene(this.getClass(), event, "CoverLetter.fxml");
            }
            else if (item.getValue().equals("CV")){
                SceneSwitcher.switchScene(this.getClass(), event, "CV.fxml");
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

    public void submitButton(ActionEvent event) throws IOException{
        String content = editArea.getText();
        List<Document> documents = ((Applicant)Main.user).getDocuments();
        Document newDocument = CoverLetter.createByDirectInput("cover letter.txt", content, Main.system.now());
        boolean added = false;
        for (Document document: documents) {
            if (document instanceof CoverLetter){
                document = newDocument;
                added = true;
            }
        }
        if (added == false) {
            ((Applicant) Main.user).addDocument(newDocument);
            SceneSwitcher.switchScene(this.getClass(), event, "CoverLetter.fxml");
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
