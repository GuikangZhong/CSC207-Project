package project.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import project.application.*;
import project.user.Applicant;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class RefereeMenu extends ApplicationController implements Serializable {
    private static final long serialVersionUID = -5054910626456091917L;

    @FXML
    ListView<Applicant> applicants;
    Applicant applicant;

    private FileChooser fc = new FileChooser();

    @Override
    void postInit() {
        super.postInit();
        applicants.setCellFactory(CellFactoryFactory.getCellFactoryForApplicant());
        pollApplicants();
    }

    public void uploadButton(ActionEvent event) throws IOException {
        if (applicant == null) {
            showModal("No applicant selected");
            return;
        }
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Document document = DocumentFactory.createByFileName(selectedFile.getName(), selectedFile.getAbsolutePath(), getSystem().now(), "ReferenceLetter");
            if (!(applicant.addDocument(document))) {
                showModal("Cannot add document");
            } else {
                showModal("Successfully added");
            }
        } else {
            showModal("File not selected");
        }
    }


    private void pollApplicants() {
        HashMap<String, Applicant> allPostings = getSystem().getApplicants();
        applicants.getItems().addAll(allPostings.values());
    }

    public void applicantsClicked(MouseEvent event) {
        applicant = applicants.getSelectionModel().getSelectedItem();
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
