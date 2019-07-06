package project.gui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HRMenuController implements Initializable {
    @FXML
    private TreeView<String> options;
    @FXML
    private Label companyName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> option = new TreeItem<>("Option");
        TreeItem<String> document = new TreeItem<>("View all applicants");
        TreeItem<String> jobPosting = new TreeItem<>("View applicants for a job posting");
        TreeItem<String> application = new TreeItem<>("Inspect interviewers"); // Maybe change the name in the future.....
        TreeItem<String> history = new TreeItem<>("Interview status");  // Handout P3 Paragraph 2
        companyName.setText(Main.user.getCompany());

        option.getChildren().addAll(document, jobPosting, application, history);
        option.setExpanded(true);

        options.setRoot(option);
    }

    public void selectItems(MouseEvent event) throws IOException {
        TreeItem<String> item = options.getSelectionModel().getSelectedItem();
//        if (item.getValue().equals("Sign out")){
//            Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));
//            Scene mainScene = new Scene(main);
//            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            loginWindow.setScene(mainScene);
//            loginWindow.show();
//        }
        if (item != null){
            if (item.getValue().equals("View all applicants")){
                SceneSwitcher.switchScene(this.getClass(), event, "AllApplicants.fxml");
            }
            if (item.getValue().equals("View applicants for a job posting")){
                SceneSwitcher.switchScene(this.getClass(), event, "PostingApplicants.fxml");
            }
            if (item.getValue().equals("Inspect interviewers")){
                SceneSwitcher.switchScene(this.getClass(), event, "Miscellaneous.fxml");
            }
        }
    }

    public void exit(Event event) throws IOException{
        SceneSwitcher.switchScene(this.getClass(), event, "Main.fxml");
    }
}
