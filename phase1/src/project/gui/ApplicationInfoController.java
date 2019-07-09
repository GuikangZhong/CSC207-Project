package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import project.application.Application;
import project.application.Document;

import javax.print.Doc;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationInfoController implements Initializable {
    @FXML
    private Label applicantName;
    @FXML
    private Label documentName;
    @FXML
    private ListView<Document> documents;
    @FXML
    private TextArea content;
    Application application;

    void setApplication(Application application) {
        this.application = application;
        applicantName.setText(application.getApplicant().getRealName());
        documents.getItems().clear();
        documents.getItems().addAll(application.getDocument());
        content.setDisable(true);

    }

    public void documentsClicked(MouseEvent event) {
        Document document = documents.getSelectionModel().getSelectedItem();
        if (null != document) {
            documentName.setText(document.getName());
            content.setText(document.getContent());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
