package project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class typeController implements Initializable {

    @FXML
    ChoiceBox<String> type;
    @FXML
    private Button confirm;
    @FXML
    private String fileName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("Applicant", "HR", "Interviewer");
        type.setValue("Applicant");
    }

    public void returnButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }

    public void confirmButton(ActionEvent event) throws IOException {
        SceneSwitcher.switchScene(this.getClass(), event, "SignUpPage.fxml");
    }

    public void typeSelected(MouseEvent event) throws IOException {
        String item = type.getSelectionModel().getSelectedItem();
        if (item != null){
            if (item.equals("Applicant")) {
                this.fileName = item;
            }
        }
    }
}
