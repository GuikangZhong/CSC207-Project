package project.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.application.CV;
import project.application.CoverLetter;
import project.application.Document;
import project.user.Applicant;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DocumentController implements Initializable {
    @FXML
    private TreeView<String> options;
    @FXML
    private ListView<Document> documentList;
    @FXML
    private TextArea description;
    @FXML
    private TextArea fileName;

    private FileChooser fc = new FileChooser();

    private void pollDocuments(){
        // fill the document list
        List<Document> documents = ((Applicant)Main.user).getDocuments();
        if (documents.size() != 0) {
            for (Document document1: documents){
                documentList.getItems().add(document1);
            }
        }
    }
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

        pollDocuments();
    }

    public void selectItems(MouseEvent event) throws IOException{
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
        if (item != null){
            if (item.getValue().equals("Document")) {
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

    public void selectDocument(MouseEvent event) throws IOException {
        Document document = documentList.getSelectionModel().getSelectedItem();
        if (document != null) {
            fileName.setText(document.getName());
            description.setText(document.getContent());
        }
    }

    public void uploadButton(ActionEvent event) throws IOException{
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            CoverLetter coverLetter = CoverLetter.createByFileName(selectedFile.getName(), selectedFile.getAbsolutePath(), Main.system.now());
            ((Applicant)Main.user).addDocument(coverLetter);
            SceneSwitcher.switchScene(this.getClass(), event, "Document.fxml");
        }
        else{
            System.out.println("File not selected");
        }
    }

    public void saveButton(ActionEvent event) throws IOException{
        CoverLetter coverLetter = CoverLetter.createByDirectInput(fileName.getText(), description.getText(), Main.system.now());
        ((Applicant)Main.user).addDocument(coverLetter);
        System.out.println("save successfully!");
    }


    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
