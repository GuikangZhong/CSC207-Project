package project.gui.ApplicantGUIs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import project.application.Application;
import project.application.Document;

public class ApplicationInfoController {
    @FXML
    private Label applicantName;
    @FXML
    private Label documentName;
    @FXML
    private ListView<Document> documents;
    @FXML
    private TextArea content;
    Application application;

    public void setApplication(Application application) {
        this.application = application;
        applicantName.setText(application.getApplicant().getRealName());
        documents.getItems().clear();
        documents.getItems().addAll(application.getDocument());
        content.setEditable(false);

    }

    public void documentsClicked(MouseEvent event) {
        Document document = documents.getSelectionModel().getSelectedItem();
        if (null != document) {
            documentName.setText(document.getName());
            content.setText(document.getContent());
        }
    }
}
