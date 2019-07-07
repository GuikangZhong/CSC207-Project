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

public class DocumentController  extends ApplicationController implements Initializable {
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
        List<Document> documents = ((Applicant)getUser()).getDocuments();
        if (documents.size() != 0) {
            for (Document document1: documents){
                documentList.getItems().add(document1);
            }
        }
    }


    @Override
    public void postInit(){
        super.postInit();
        pollDocuments();
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
            CoverLetter coverLetter = CoverLetter.createByFileName(selectedFile.getName(), selectedFile.getAbsolutePath(), getSystem().now());
            ((Applicant)getUser()).addDocument(coverLetter);
            SceneSwitcher.switchScene(this, event, "Document.fxml");
        }
        else{
            System.out.println("File not selected");
        }
    }

    public void saveButton(ActionEvent event) throws IOException{
        CoverLetter coverLetter = CoverLetter.createByDirectInput(fileName.getText(), description.getText(), getSystem().now());
        ((Applicant)getUser()).addDocument(coverLetter);
        System.out.println("save successfully!");
    }


    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
