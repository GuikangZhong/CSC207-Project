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

public class ApplicantMenuController extends ApplicationController {


    public void uploadButton(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            CoverLetter coverLetter = CoverLetter.createByFileName(selectedFile.getName(),
                    selectedFile.getAbsolutePath(), getSystem().now());
            ((Applicant) getUser()).addDocument(coverLetter);
        } else {
            System.out.println("file not exist");
        }
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
