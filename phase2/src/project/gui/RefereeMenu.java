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
import project.user.HR;

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
        applicants.setCellFactory(new Callback<ListView<Applicant>, ListCell<Applicant>>() {

            @Override
            public ListCell<Applicant> call(ListView<Applicant> p) {

                ListCell<Applicant> cell = new ListCell<Applicant>() {

                    @Override
                    protected void updateItem(Applicant t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getUsername());
                        } else {
                            setText("");
                        }
                    }

                };
                return cell;
            }
        });
        pollApplicants();
    }

    public void uploadButton(ActionEvent event) throws IOException {
        if (applicant == null) {
            showModal("No applicant selected");
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
        for (Map.Entry mapElement : allPostings.entrySet()) {
            applicants.getItems().add((Applicant) mapElement.getValue());
        }
    }

    public void applicantsClicked(MouseEvent event) {
        applicant = applicants.getSelectionModel().getSelectedItem();
    }

    public void exit(Event event) throws IOException {
        SceneSwitcher.switchScene(this, event, "Main.fxml");
    }
}
