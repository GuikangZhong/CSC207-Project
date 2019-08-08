package project.gui.ApplicantGUIs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import project.application.*;

import java.util.ArrayList;
import java.util.List;

public class ApplicationInfoController {
    @FXML
    private Label applicantName;
    @FXML
    private Label documentName;
    @FXML
    private ListView<Document> documents;
    @FXML
    private TextArea content;

    public void setApplication(Application application) {
        applicantName.setText(application.getApplicant().getRealName());
        documents.getItems().clear();
        documents.getItems().addAll(docListAdded(application));
        content.setEditable(false);

    }

    public void documentsClicked(MouseEvent event) {
        Document document = documents.getSelectionModel().getSelectedItem();
        if (null != document) {
            documentName.setText(document.getName());
            content.setText(document.getContent());
        }
    }

    /**@param application: The application that the HR manager is currently viewing.
     *
     * @return the list of all the CV's, Cover Letters, and the reference letters in the application that is
     * for the specific job posting currently viewed by the HR.
     */
    private List<Document> docListAdded(Application application){
        List<Document> temp = new ArrayList<>();
        for (Document document: application.getDocument()){
            if (document.getDocumentType().equals(CV.documentType())){
                temp.add(document);
            }
            else if (document.getDocumentType().equals(CoverLetter.documentType())){
                temp.add(document);
            }
            else if (document.getDocumentType().equals(ReferenceLetter.documentType())
            && (((ReferenceLetter) document).getJobPosting() == application.getJobPosting())){
                temp.add(document);
            }
        }
        return temp;
    }
}
